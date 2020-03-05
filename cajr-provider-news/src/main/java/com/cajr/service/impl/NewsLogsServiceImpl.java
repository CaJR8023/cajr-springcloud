package com.cajr.service.impl;

import com.cajr.mapper.NewsLogsMapper;
import com.cajr.service.NewsLogsService;
import com.cajr.vo.news.NewsLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/3 10:09 上午
 */
@Service
public class NewsLogsServiceImpl implements NewsLogsService {

    @Autowired
    private NewsLogsMapper newsLogsMapper;

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
        return this.newsLogsMapper.insertSelective(newsLogs);
    }

    @Override
    public Integer update(NewsLogs newsLogs) {
        return this.newsLogsMapper.updateByPrimaryKeySelective(newsLogs);
    }
}
