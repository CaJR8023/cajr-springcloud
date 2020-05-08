package com.cajr.service.impl;

import com.cajr.mapper.UserInfoMapper;
import com.cajr.service.UserInfoService;
import com.cajr.service.UserService;
import com.cajr.vo.user.User;
import com.cajr.vo.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author CAJR
 * @date 2020/5/7 5:00 下午
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer update(UserInfo userInfo) {
        User user = this.userService.getUser(userInfo.getUserId());
        if (!user.getUsername().equals(userInfo.getUserName())){
            user.setUsername(userInfo.getUserName());
            user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            this.userService.update(user);
        }
        userInfo.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userInfoMapper.updateByUserId(userInfo);
    }
}
