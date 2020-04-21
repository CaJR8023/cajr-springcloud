package com.cajr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cajr.mapper.NewsMapper;
import com.cajr.service.IUserClientService;
import com.cajr.service.NewsHotAndNewestService;
import com.cajr.service.NewsLogsService;
import com.cajr.service.NewsService;
import com.cajr.util.CommonParam;
import com.cajr.util.NewsFillDataUtil;
import com.cajr.util.SubListUtil;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.News;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author CAJR
 * @date 2020/4/6 9:30 下午
 */
@Service
public class NewsHotAndNewestServiceImpl implements NewsHotAndNewestService {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsLogsService newsLogsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IUserClientService iUserClientService;

    @Override
    public void hotNewsExtract() {

    }

    @Override
    public void newestNews() {
        List<News> newsList = this.newsService.findAllByPage(1, 5000).getList();
        newsList.forEach(news -> {
            this.redisTemplate.opsForZSet().add(CommonParam.HOT_NEWS_REDIS_KEY, String.valueOf(news.getId()), news.getVisitorCount()+news.getReviewCount());
        });
    }

    @Override
    public PageInfo newestNewsExtract(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo<News> pageInfo = new PageInfo<>(this.newsMapper.selectAllSortByTime());
        if (!pageInfo.getList().isEmpty()){
            List<News> newsList = pageInfo.getList();
            newsList.forEach(news -> {
                NewsFillDataUtil.fillNews(news);
                news.setUserOther(this.iUserClientService.getOneUserOther(news.getUserId()));
            });
            pageInfo.setList(newsList);
        }

        return pageInfo;
    }

    @Override
    public List<News> get24HoursHotNews() {
        int index = 0;
        Set<ZSetOperations.TypedTuple<Object>> hotNewsRedisTopIds = redisTemplate.opsForZSet().reverseRangeWithScores(CommonParam.HOT_NEWS_REDIS_KEY,0,-1);
        List<Integer> hotNewsTopIds = new ArrayList<>();
        for (ZSetOperations.TypedTuple<Object> hotNewsTopId : hotNewsRedisTopIds) {
            if (index < 5){
                hotNewsTopIds.add(Integer.parseInt((String) Objects.requireNonNull(hotNewsTopId.getValue())));
                index++;
            }else {
                break;
            }
        }
        List<News> newsList = this.newsService.findSectionNews(hotNewsTopIds);
        if (!newsList.isEmpty()) {
            newsList.forEach(NewsFillDataUtil::fillNews);
        }
        return newsList;
    }

    private void sortByTime(List<News> newsList){
        newsList.sort((o1, o2) -> {
            if (o1.getCreatedAt().after(o2.getCreatedAt())) {
                return 1;
            } else {
                return 0;
            }
        });
    }
}
