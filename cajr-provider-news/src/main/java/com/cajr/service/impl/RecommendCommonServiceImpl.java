package com.cajr.service.impl;

import com.cajr.service.*;
import com.cajr.util.CustomHashMap;
import com.cajr.util.JsonUtil;
import com.cajr.util.TimeUtil;
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
        newsList.forEach(news -> {
            if (news.getCreatedAt().before(getInRecTimestamp())){
                newsIds.remove(news.getId());
            }
        });
    }

    @Override
    public HashMap<Integer, CustomHashMap<Integer, CustomHashMap<String, Double>>> getUserPrefList(List<Integer> userIds) {
        HashMap<Integer, CustomHashMap<Integer, CustomHashMap<String, Double>>> userPrefListMap = new HashMap<>(16);
        if (userIds.isEmpty()){
            return userPrefListMap;
        }
        List<User> users = this.iUserClientService.findSectionUserId(userIds);
        if (!users.isEmpty()){
            users.forEach(user -> {
                userPrefListMap.put(user.getId(), JsonUtil.jsonPrefListToMap(user.getUserPref().getPrefList()));
            });
        }
        return userPrefListMap;
    }
}
