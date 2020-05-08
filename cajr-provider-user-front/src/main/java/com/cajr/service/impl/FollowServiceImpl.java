package com.cajr.service.impl;

import com.cajr.mapper.FollowMapper;
import com.cajr.service.FollowService;
import com.cajr.service.UserService;
import com.cajr.vo.user.Follow;
import com.cajr.vo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/5/8 1:23 下午
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<User> getFollowUsers(int userId) {
        List<Follow> follows = this.followMapper.selectAll(userId);
        List<User> users = new ArrayList<>();
        if (CollectionUtils.isEmpty(follows)){
            return users;
        }
        follows.forEach(follow -> {
            users.add(this.userService.getUser(follow.getFollowedId()));
        });
        return users;
    }

    @Override
    public int follow(Follow follow) {
        follow.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        if (this.followMapper.checkExisted(follow) > 0){
            return this.followMapper.updateByPrimaryKeySelective(follow);
        }
        follow.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.followMapper.insertSelective(follow);
    }
}
