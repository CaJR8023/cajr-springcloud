package com.cajr.service.impl;

import com.cajr.algorithm.TFIDF;
import com.cajr.service.*;
import com.cajr.util.CustomHashMap;
import com.cajr.util.JsonUtil;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsLogs;
import com.cajr.vo.user.User;
import com.cajr.vo.user.UserPref;
import org.ansj.app.keyword.Keyword;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author CAJR
 * @date 2020/3/3 7:08 下午
 */
@Service("userPrefRefresherServiceImpl")
public class UserPrefRefresherServiceImpl implements UserPrefRefresherService {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(UserPrefRefresherServiceImpl.class);

    //设置TFIDF提取的关键字数目
    public static final int KEY_WORDS_NUM = 10;

    //喜好值低于这个值就被淘汰
    private static final int KEY_DELETE_VALUE = 10;

    //每日衰减系数coefficient
    private static final double DEC_COEFFICIENT=0.7;

    //筛选用户前几天的浏览记录
    private static final int OLD_BROWSE_DAY = -1;

    @Resource
    private IUserClientService iUserClientService;

    @Autowired
    private NewsLogsService newsLogsService;

    @Autowired
    private RecommendCommonService recommendCommonService;

    @Autowired
    private NewsService newsService;

    @Override
    public void refresh() {
        refresh(this.iUserClientService.findAllUserId());
    }

    /**
     * 按照推荐频率调用的方法，一般为一天执行一次
     * 定期根据前一天所有用户的浏览记录，在对用户进行喜好关键词列表TFIDF值的衰减，将用户前一天看的新闻的关键词及相应TFIDF值更新到列表中去
     * @param userIds 用户id
     */
    private void refresh(List<Integer> userIds){
        //首先对用户的喜好关键词列表进行衰减
        autoDecRefresh(userIds);
        //用户浏览记录
        Map<Integer, List<Integer>> userBrowsedMap = getBrowsedHistoryMap();
        if (userBrowsedMap.isEmpty()){
            return;
        }

        //获取用户喜好关键词
        Map<Integer,CustomHashMap<Integer, CustomHashMap<String, Double>>> userPrefListMap = this.recommendCommonService.getUserPrefList(new ArrayList<>(userBrowsedMap.keySet()));
        //新闻对应关键词列表与模块ID
        Map<String, Object> newsTFIDFMap =getNewsTFIDFMap();

        //开始遍历用户浏览记录，更新用户喜好关键词列表
        //对每个用户，循环他看过的每条新闻，对每条新闻，更新它的关键词列表到用户的对应的模块中
        List<Integer> browseUserIds = new ArrayList<>(userBrowsedMap.keySet());
        for (Integer userId : browseUserIds){
            List<Integer> newsIds = userBrowsedMap.get(userId);
            if (!newsIds.isEmpty()){
                for (Integer newsId : newsIds) {
                    Integer moduleId = (Integer) newsTFIDFMap.get(newsId+"moduleId");

                    //获得对应模块喜好关键词的map
                    CustomHashMap<String, Double> rateMap = userPrefListMap.get(userId).get(moduleId);
                    logger.info(userId + "模块喜好关键词的map" + rateMap.toString());
                    //获取新闻关键字的List
                    List<Keyword> keywordList = (List<Keyword>) newsTFIDFMap.get(newsId.toString());
                    if (!keywordList.isEmpty()){
                        for (Keyword keyword : keywordList) {
                            String name = keyword.getName();
                            //如果有重复的关键词，在原有的基础分上加
                            if (rateMap.containsKey(name)){
                                rateMap.put(name, rateMap.get(name)+keyword.getScore());
                            }else {
                                rateMap.put(name, keyword.getScore());
                            }
                        }
                        userPrefListMap.get(userId).get(moduleId).putAll(rateMap);
                    }
                }
                UserPref userPref = new UserPref();
                userPref.setUserId(userId);
                userPref.setPrefList(userPrefListMap.get(userId).toString());
                this.iUserClientService.updateUserPref(userPref);
            }
        }

    }

    @Override
    public void autoDecRefresh() {
        autoDecRefresh(this.iUserClientService.findAllUserId());
    }

    private void autoDecRefresh(List<Integer> userIdList) {
        List<User> users = this.iUserClientService.findSectionUserId(userIdList);
        if (users.isEmpty()){
            return;
        }
        //用于删除喜好值过低的关键词
        List<String> keyWordToDelete = new ArrayList<>();
        users.parallelStream().forEach(user -> {
            StringBuilder newsPrefList = new StringBuilder("{");
            HashMap<Integer, CustomHashMap<String, Double>> map = JsonUtil.jsonPrefListToMap(user.getUserPref().getPrefList());
            for (Integer moduleId : map.keySet()) {
                //用户对应模块不为空
                CustomHashMap<String, Double> moduleMap = map.get(moduleId);
                newsPrefList.append("\"").append(moduleId).append("\":");
                if (!("{}".equals(moduleMap.toString()))) {
                    Iterator<String> stringIterator = moduleMap.keySet().iterator();
                    while (stringIterator.hasNext()) {
                        String key = stringIterator.next();
                        //累计TFIDF值*衰减因子
                        double result = moduleMap.get(key) * DEC_COEFFICIENT;
                        if (result < KEY_DELETE_VALUE) {
                            keyWordToDelete.add(key);
                        }
                        moduleMap.put(key, result);
                    }
                }

                if (!keyWordToDelete.isEmpty()) {
                    for (String deleteKey : keyWordToDelete) {
                        moduleMap.remove(deleteKey);
                    }
                }
                keyWordToDelete.clear();
                newsPrefList.append(moduleMap.toString()).append(",");
            }
            newsPrefList.append(",").append(newsPrefList.substring(0,newsPrefList.length())).append("}");
            UserPref userPref = new UserPref();
            userPref.setUserId(user.getId());
            userPref.setPrefList(newsPrefList.toString());
            //更新用户喜好关键词列表
            this.iUserClientService.updateUserPref(userPref);
        });

    }

    /**
     * 提取出当天有浏览行为的用户及其各自所浏览过的新闻id列表
     * @return
     */
    private Map<Integer,List<Integer>> getBrowsedHistoryMap() {
        Map<Integer,List<Integer>> userBrowsedMap = new HashMap<>(16);
        List<NewsLogs> newsLogsList = this.newsLogsService.findAll();
        if (newsLogsList.isEmpty()){
            return userBrowsedMap;
        }
        for (NewsLogs newsLogs : newsLogsList) {
            if (newsLogs.getViewTime().after(TimeUtil.getInRecTimestamp(OLD_BROWSE_DAY))){
                userBrowsedMap.put(newsLogs.getUserId(), new ArrayList<Integer>());
                userBrowsedMap.get(newsLogs.getUserId()).add(newsLogs.getNewsId());
            }
        }
        return userBrowsedMap;
    }

    /**
     *  将所有当天被浏览过的新闻提取出来，以便进行TFIDF求值操作，以及对用户喜好关键词列表的更新。
     * @return
     */
    private Map<String, Object> getNewsTFIDFMap(){
        Map<String, Object> newsTFIDFMap = new HashMap<>(16);
        List<Integer> newsIdList = getBrowsedNewsList();
        List<News> newsList = this.newsService.findSectionNews(newsIdList);
        if (!newsIdList.isEmpty()){
            newsList.forEach(news -> {
                newsTFIDFMap.put(String.valueOf(news.getId()), TFIDF.getTfidf(news.getTitle(), news.getContent(), KEY_WORDS_NUM));
                newsTFIDFMap.put(news.getId()+"moduleId", news.getModuleId());
            });
        }
        return newsTFIDFMap;
    }

    /**
     * 获得浏览过的新闻的列表
     * @return
     */
    private List<Integer> getBrowsedNewsList() {
        List<Integer> newsIdList = new ArrayList<>();
        Map<Integer, List<Integer>> browsedMap = getBrowsedHistoryMap();
        Iterator<Integer> iterator = getBrowsedHistoryMap().keySet().iterator();
        while (iterator.hasNext()){
            List<Integer> newsIds = browsedMap.get(iterator.next());
            newsIdList.addAll(newsIds);
        }
        return newsIdList;
    }

    public static void main(String[] args) {
        Map<Integer,List<Integer>> userBrowsedMap = new HashMap<>(16);

    }
}
