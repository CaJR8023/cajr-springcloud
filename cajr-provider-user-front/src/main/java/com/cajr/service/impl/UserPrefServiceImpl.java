package com.cajr.service.impl;

import com.cajr.mapper.UserPrefMapper;
import com.cajr.service.UserPrefService;
import com.cajr.vo.user.UserPref;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author CAJR
 * @date 2020/3/3 3:16 下午
 */
@Service
public class UserPrefServiceImpl implements UserPrefService{

    @Autowired
    UserPrefMapper userPrefMapper;

    @Override
    public Integer add(UserPref userPref) {
        userPref.setStatus(1);
        userPref.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userPref.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userPrefMapper.insertSelective(userPref);
    }

    @Override
    public Integer update(UserPref userPref) {
        userPref.setStatus(1);
        userPref.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userPrefMapper.updateByPrimaryKeySelective(userPref);
    }
}
