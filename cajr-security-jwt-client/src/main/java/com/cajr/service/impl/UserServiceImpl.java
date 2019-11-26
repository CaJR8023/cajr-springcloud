package com.cajr.service.impl;

import com.cajr.mapper.AdminMapper;
import com.cajr.mapper.UserMapper;
import com.cajr.service.UserService;
import com.cajr.vo.admin.Admin;
import com.cajr.vo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author CAJR
 * @date 2019/11/26 12:34 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;


    @Override
    public Optional<User> getOneUserByTel(String tel) {
        return Optional.empty();
    }

    @Override
    public Optional<Admin> getOneAdminByAccount(String account) {
        return Optional.empty();
    }
}
