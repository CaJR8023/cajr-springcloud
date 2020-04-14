package com.cajr.service.impl;

import com.cajr.mapper.NewsImageMapper;
import com.cajr.service.NewsImageService;
import com.cajr.vo.news.NewsImage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author CAJR
 * @date 2020/1/17 4:05 下午
 */
@Service
public class NewsImageServiceImpl implements NewsImageService {

    @Autowired
    NewsImageMapper newsImageMapper;

    @Override
    public Integer add(NewsImage newsImage) {

        newsImage.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        newsImage.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        newsImage.setStatus(1);
        return newsImageMapper.insertSelective(newsImage);
    }

    @Override
    public PageInfo getAllByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(this.newsImageMapper.findAll());
    }
}
