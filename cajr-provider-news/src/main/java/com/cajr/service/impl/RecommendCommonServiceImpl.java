package com.cajr.service.impl;

import com.cajr.service.*;
import com.cajr.util.CustomHashMap;
import com.cajr.util.JsonUtil;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.Module;
import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsLogs;
import com.cajr.vo.news.NewsRecommend;
import com.cajr.vo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/2 5:39 下午
 */
@Service
public class RecommendCommonServiceImpl implements RecommendCommonService {

    @Autowired
    private NewsLogsService newsLogsService;

    @Autowired
    private NewsRecommendService newsRecommendService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private ModuleService moduleService;

    @Resource
    private IUserClientService iUserClientService;

    /**
     * 推荐新闻的时效性天数，即从推荐当天开始到之前beforeDays天的新闻属于仍具有时效性的新闻，予以推荐。
     */
    @Value("${news.recommend.beforeDays}")
    private int beforeDays;


    @Override
    public void filterBrowsedNews(List<Integer> newsIds, Integer userId) {
        List<NewsLogs> newsLogs = this.newsLogsService.findAllByUserId(userId);
        if (newsLogs.isEmpty()){
            return;
        }
        newsLogs.forEach(newsLog -> {
            if (newsIds.contains(newsLog.getNewsId())){
                newsIds.remove(newsLog.getNewsId());
            }
        });
    }

    @Override
    public void filterRecCedNews(List<Integer> newsIds, Integer userId) {
        List<NewsRecommend> newsRecommends = this.newsRecommendService.findAllByUserId(userId);
        if (newsRecommends.isEmpty()){
            return;
        }
        List<NewsRecommend> newsRecommendList = new ArrayList<>();
        newsRecommends.forEach(newsRecommend -> {
            if (newsRecommend.getCreatedAt().after(getInRecDate())){
                newsRecommendList.add(newsRecommend);
            }
        });
        if (newsRecommendList.isEmpty()){
            return;
        }
        newsRecommendList.forEach(newsRecommend -> {
            if (newsIds.contains(newsRecommend.getNewsId())){
                newsIds.remove(newsRecommend.getNewsId());
            }
        });
    }

    private Date getInRecDate() {
        return TimeUtil.getSpecificDayFormatForDate(beforeDays);
    }

    private Timestamp getInRecTimestamp() {
        return TimeUtil.getInRecTimestamp(beforeDays);
    }

    @Override
    public void filterOutDateNews(List<Integer> newsIds, Integer userId) {
        List<News> newsList = this.newsService.findSectionNews(newsIds);
        if (newsList.isEmpty()){
            return;
        }

        for (News news : newsList) {
            if (news.getCreatedAt().before(getInRecTimestamp())){
                newsIds.remove(Integer.valueOf(news.getId()));
            }
        }
    }

    @Override
    public HashMap<Integer, CustomHashMap<Integer, CustomHashMap<String, Double>>> getUserPrefList(List<Integer> userIds) {
        HashMap<Integer, CustomHashMap<Integer, CustomHashMap<String, Double>>> userPrefListMap = new HashMap<>(16);
        if (userIds.isEmpty()){
            return userPrefListMap;
        }

        List<User> users = new ArrayList<>();
        for (Integer userId : userIds) {
            User user = iUserClientService.getUser(userId);
            if (user != null){
                if (user.getUserPref() != null){
                    users.add(user);
                }
            }
        }
        if (!users.isEmpty()){
            users.forEach(user -> {
                userPrefListMap.put(user.getId(), JsonUtil.jsonPrefListToMap(user.getUserPref().getPrefList()));
            });
        }
        return userPrefListMap;
    }

    @Override
    public String getDefaultUserPref() {
        List<Module> modules = this.moduleService.findAll();
        if (modules.isEmpty()){
            return "{}";
        }
        StringBuilder userPrefList = new StringBuilder("{");
        for (Module module : modules) {
            userPrefList.append("\"").append(module.getId()).append("\":").append("{}").append(",");
        }

        return userPrefList.substring(0,userPrefList.length()-1) + "}";
    }
}
