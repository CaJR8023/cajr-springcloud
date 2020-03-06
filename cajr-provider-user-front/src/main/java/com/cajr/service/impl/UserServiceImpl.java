package com.cajr.service.impl;

import com.cajr.mapper.UserMapper;
import com.cajr.service.UserService;
import com.cajr.util.CustomHashMap;
import com.cajr.util.TimeUtil;
import com.cajr.vo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author CAJR
 * @date 2019/11/26 2:06 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Value("${news.recommend.activeDays}")
    private int activeDays;

    @Override
    public Optional<User> getOneUser(int id) {
        return Optional.of(this.userMapper.selectByPrimaryKey(id));
    }

    @Override
    public User getUser(int id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Optional<Integer> add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStatus(1);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return Optional.of(this.userMapper.insertSelective(user));
    }

    @Override
    public Optional<Integer> update(User user) {

        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return Optional.of(this.userMapper.updateByPrimaryKey(user));
    }

    @Override
    public Optional<Integer> deleteByStatus(int id) {
        return Optional.of(this.userMapper.deleteByPrimaryKey(id));
    }

    @Override
    public Optional<Integer> checkIsExistByTel(String tel) {
        return Optional.of(this.userMapper.checkIsExistsByTel(tel));
    }

    @Override
    public Optional<Integer> checkIsExistById(int id) {
        return Optional.of(this.userMapper.checkIsExistsById(id));
    }

    @Override
    public List<Integer> findAllUserId() {
        return this.userMapper.findAllUserId();
    }

    @Override
    public List<User> findSection(List<Integer> userIds) {
        return null;
    }

    @Override
    public List<Integer> findAllActiveUserId() {
        List<User> users = this.userMapper.findAll();
        if (users.isEmpty()){
            return null;
        }
        List<Integer> activeUserIds = new ArrayList<>();
        users.forEach(user -> {
            if (user.getUserLog().getLatestLoginTime().after(TimeUtil.getInRecTimestamp(activeDays))){
                activeUserIds.add(user.getId());
            }
        });
        return activeUserIds;
    }
}
