package com.cajr.service.impl;

import com.cajr.mapper.NewsUserMapper;
import com.cajr.service.NewsService;
import com.cajr.service.NewsUserService;
import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/5/8 4:01 下午
 */
@Service
public class NewsUserServiceImpl implements NewsUserService {

    @Autowired
    private NewsUserMapper newsUserMapper;

    @Autowired
    private NewsService newsService;

    @Override
    public List<News> getByUserId(int userId) {
        List<NewsUser> newsUsers = this.newsUserMapper.selectAllByUserId(userId);
        if (CollectionUtils.isEmpty(newsUsers)){
            return null;
        }
        List<News> newsList = new ArrayList<>();
        newsUsers.forEach(newsUser -> {
            newsList.add(this.newsService.getOne(newsUser.getNewsId()));
        });
        return newsList;
    }

    @Override
    public int collect(NewsUser newsUser) {
        newsUser.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        if (this.newsUserMapper.checkExisted(newsUser) > 0){
            return this.newsUserMapper.updateByPrimaryKeySelective(newsUser);
        }
         newsUser.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.newsUserMapper.insertSelective(newsUser);
    }
}
