package com.cajr.service.impl;

import com.cajr.mapper.UserLogMapper;
import com.cajr.service.UserLogService;
import com.cajr.vo.user.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author CAJR
 * @date 2020/3/3 11:48 上午
 */
@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public UserLog getByUserId(int userId) {
        return null;
    }

    @Override
    public Integer add(UserLog userLog) {
        userLog.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userLog.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userLogMapper.insertSelective(userLog);
    }

    @Override
    public Integer updateUserId(UserLog userLog) {
        userLog.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userLogMapper.updateByUserIdSelective(userLog);
    }
}
