package com.cajr.service.impl;

import com.cajr.mapper.NewsMapper;
import com.cajr.service.NewsService;
import com.cajr.vo.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CAJR
 * @date 2020/1/15 4:43 下午
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Override
    public Integer add(News news) {
        return newsMapper.insertSelective(news);
    }
}
