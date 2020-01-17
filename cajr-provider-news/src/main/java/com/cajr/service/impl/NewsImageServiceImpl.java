package com.cajr.service.impl;

import com.cajr.mapper.NewsImageMapper;
import com.cajr.service.NewsImageService;
import com.cajr.vo.news.NewsImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return newsImageMapper.insertSelective(newsImage);
    }
}
