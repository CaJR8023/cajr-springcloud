package com.cajr.service.impl;

import com.cajr.mapper.UserNewsStarMapper;
import com.cajr.service.UserNewsStarService;
import com.cajr.vo.user.UserNewsStar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author CAJR
 * @date 2020/5/8 1:49 下午
 */
@Service
public class UserNewsStarServiceImpl implements UserNewsStarService {

    @Autowired
    private UserNewsStarMapper userNewsStarMapper;



    @Override
    public int star(UserNewsStar userNewsStar) {
        userNewsStar.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        if (this.userNewsStarMapper.checkExisted(userNewsStar) > 0){
            this.userNewsStarMapper.updateByPrimaryKeySelective(userNewsStar);
        }
        userNewsStar.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userNewsStarMapper.insertSelective(userNewsStar);
    }

    @Override
    public UserNewsStar getByUserIdAndNews(int userId, int newsId) {
        return this.userNewsStarMapper.selectByUserIdAndNewsId(userId, newsId);
    }
}
