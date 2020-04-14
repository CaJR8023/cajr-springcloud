package com.cajr.service.impl;

import com.cajr.lock.DistributedLock;
import com.cajr.lock.impl.RedisDistributedLock;
import com.cajr.mapper.NewsLogsMapper;
import com.cajr.service.NewsLogsService;
import com.cajr.util.CommonParam;
import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsLogs;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/3 10:09 上午
 */
@Service
public class NewsLogsServiceImpl implements NewsLogsService {

    @Autowired
    private NewsLogsMapper newsLogsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String LOCK_KEY = "news_logs_lock";

    @Override
    public List<NewsLogs> findAllByUserId(Integer userId) {
        return this.newsLogsMapper.findAllByUserId(userId);
    }

    @Override
    public List<NewsLogs> findAll() {
        return this.newsLogsMapper.findAll();
    }

    @Override
    public Integer add(NewsLogs newsLogs) {
        String lockLogo = null;
        Integer result;
        //加锁
        DistributedLock distributedLock = new RedisDistributedLock(redisTemplate,LOCK_KEY,60);
        try {
            do {
                lockLogo = distributedLock.acquireLock();
            } while (lockLogo == null);
            //对redis中的热点新闻zset的分数进行加一
            BoundZSetOperations boundGeoOperations = redisTemplate.boundZSetOps(CommonParam.HOT_NEWS_REDIS_KEY);
            boundGeoOperations.incrementScore(String.valueOf(newsLogs.getNewsId()), 1);
            result = this.newsLogsMapper.insertSelective(newsLogs);
        }finally {
            while(true){
                boolean unLock = distributedLock.releaseLock(lockLogo);
                if (unLock){
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Integer update(NewsLogs newsLogs) {
        return this.newsLogsMapper.updateByPrimaryKeySelective(newsLogs);
    }

    @Override
    public List<News> getAllNewsByUserId(Integer userId) {
        List<NewsLogs> newsLogsList = this.newsLogsMapper.findAllByUserId(userId);
        List<News> newsList = new ArrayList<>();
        if (!newsLogsList.isEmpty()){
            newsLogsList.forEach(newsLogs->{
                if (newsLogs.getNews() != null){
                    newsList.add(newsLogs.getNews());
                }
            });
        }
        return newsList;
    }

    @Override
    public Integer checkExistByUserIdAndNewsId(Integer userId, Integer newsId) {
        return this.newsLogsMapper.checkExistByUserIdAndNewsId(userId, newsId);
    }

    @Override
    public Integer delete(Integer id) {
        return this.newsLogsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo getAllByPage(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return new PageInfo<>(this.newsLogsMapper.findAll());
    }
}
