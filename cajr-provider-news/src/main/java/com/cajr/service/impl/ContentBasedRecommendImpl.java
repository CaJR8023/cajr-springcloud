package com.cajr.service.impl;

import com.cajr.algorithm.TFIDF;
import com.cajr.service.*;
import com.cajr.util.*;
import com.cajr.vo.news.News;
import org.ansj.app.keyword.Keyword;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author CAJR
 * @date 2020/3/3 5:19 下午
 */
@Service("contentBasedRecommend")
public class ContentBasedRecommendImpl implements RecommendService {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(ContentBasedRecommendImpl.class);

    @Autowired
    private UserPrefRefresherService userPrefRefresherService;

    @Autowired
    private RecommendCommonService recommendCommonService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsRecommendService newsRecommendService;

    @Value("${news.recommend.active.day}")
    private int activeDays;

    @Value("${news.recommend.TFIDFKeywordsNum}")
    private int keyWordsNum;

    @Value("${news.recommend.CBRecNum}")
    private int nNews;

    @Override
    public void recommend(List<Integer> userIds) {
        int count = 0;
        logger.info("基于内容算法开始推荐 ==> " + Timestamp.valueOf(LocalDateTime.now()));
        //首先先进行用户喜好的关键字列表的衰减更新+用户当日历史浏览记录的更新
        this.userPrefRefresherService.refresh();
        //新闻及对应关键词的Map
        Map<Integer, List<Keyword>> newsKeywordsMap = new HashMap<>();
        Map<Integer, Integer> newsModuleMap = new HashMap<>();

        //用户喜好关键词列表
        Map<Integer, CustomHashMap<Integer, CustomHashMap<String, Double>>> userPrefListMap = this.recommendCommonService.getUserPrefList(userIds);

        //获取最近7天的新闻
//        List<News> newsList = this.newsService.findAll();
        List<News> newsList = getRecentNews(CommonParam.RECENT_NEWS_BEFORE_DAYS);
        if (newsList.isEmpty()){
            return;
        }
        //提取新闻中的关键词，并把关键词放入newsKeywordsMap
        for (News news : newsList){
            if (news.getCreatedAt().after(TimeUtil.getInRecTimestamp(activeDays))){
                newsKeywordsMap.put(news.getId(), TFIDF.getTfidf(news.getTitle(),news.getContent(),keyWordsNum));
                newsModuleMap.put(news.getId(), news.getModuleId());
            }
        }

        if (newsKeywordsMap.isEmpty()){
            return;
        }

        //在遍历用户，
        for (Integer userId : userIds) {

            //新闻id与根据该用户喜好关键词匹配值的map
            Map<Integer, Double> tempMatchMap = new HashMap<>(16);
            List<Integer> newsIdList = new ArrayList<>(newsKeywordsMap.keySet());


            for (Integer newsId : newsIdList){
                Integer moduleId = newsModuleMap.get(newsId);
                if (userPrefListMap.get(userId).get(moduleId) != null){
                    tempMatchMap.put(newsId, getMatchValue(userPrefListMap.get(userId).get(moduleId), newsKeywordsMap.get(newsId)));
                }
            }

            //去除匹配值为零的新闻
            removeZeroItem(tempMatchMap);

            if (!("{}".equals(tempMatchMap.toString())) && (!tempMatchMap.isEmpty())){
                tempMatchMap = sortMapByValue(tempMatchMap);

                assert tempMatchMap != null;
                List<Integer> toBeRecededList = new ArrayList<>(tempMatchMap.keySet());

                //过滤已经推荐过的新闻
                this.recommendCommonService.filterRecCedNews(toBeRecededList, userId);
                //过滤用户已经看过的新闻
                this.recommendCommonService.filterBrowsedNews(toBeRecededList, userId);
                //如果可推荐新闻数目超过了系统默认为CB算法设置的单日推荐上限数（N），则去掉一部分多余的可推荐新闻，剩下的N个新闻才进行推荐
                if (toBeRecededList.size() > nNews){
                    RecommendKit.removeOverNews(toBeRecededList, nNews);
                }
                this.newsRecommendService.insertRecommend(toBeRecededList, userId, CommonParam.CB_ALGORITHM);
                count += toBeRecededList.size();
            }
        }
        logger.info("CB has contributed " + (count/userIds.size()) + " recommending news on average");
        logger.info("CB finished at "+new Date());
    }

    /**
     * 使用 Map按value进行排序
     * @param tempMatchMap
     * @return
     */
    private Map<Integer, Double> sortMapByValue(Map<Integer, Double> tempMatchMap) {
        if (tempMatchMap.isEmpty()){
            return null;
        }
        Map<Integer, Double> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<Integer, Double>> entryList = new ArrayList<>(tempMatchMap.entrySet());

        entryList.sort(new MapValueComparator());

        Iterator<Map.Entry<Integer, Double>> iterator = entryList.iterator();
        Map.Entry<Integer, Double> tmpEntry;
        while (iterator.hasNext()){
            tmpEntry = iterator.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }

        return sortedMap;
    }

    /**
     * 去除匹配值为零的新闻
     * @param tempMatchMap
     */
    private void removeZeroItem(Map<Integer, Double> tempMatchMap) {
        if (tempMatchMap.isEmpty()){
            return;
        }
        List<Integer> toDeleteItem = new ArrayList<>();
        for (Integer newsId : tempMatchMap.keySet()){
            if (tempMatchMap.get(newsId) <= 0){
                toDeleteItem.add(newsId);
            }
        }

        if (!toDeleteItem.isEmpty()){
            for (Integer newsId : toDeleteItem){
                tempMatchMap.remove(newsId);
            }
        }
    }

    /**
     * 获得用户的关键词列表和新闻关键词列表的匹配程度
     * @param stringDoubleCustomHashMap
     * @param keywords
     * @return
     */
    private Double getMatchValue(CustomHashMap<String, Double> stringDoubleCustomHashMap, List<Keyword> keywords) {
        Set<String> keywordsSet = stringDoubleCustomHashMap.keySet();
        double matchValue = 0.0;
        for (Keyword keyword : keywords){
            if (keywordsSet.contains(keyword.getName())){
                matchValue += keyword.getScore()*stringDoubleCustomHashMap.get(keyword.getName());
            }
        }
        return matchValue;
    }

    /**
     *  获取最近beforeDays天的新闻
     * @return
     */
    public List<News> getRecentNews(int beforeDays){
        List<News> newsAll = this.newsService.findAll();
        List<News> recentNewsList = new ArrayList<>();
        if (newsAll.isEmpty()){
            return recentNewsList;
        }
        for (News news : newsAll) {
            if (news.getCreatedAt().after(TimeUtil.getInRecTimestamp(beforeDays))){
                recentNewsList.add(news);
            }
        }

        return recentNewsList;
    }
}
