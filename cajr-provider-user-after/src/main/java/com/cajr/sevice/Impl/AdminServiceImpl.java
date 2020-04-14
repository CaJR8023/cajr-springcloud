package com.cajr.sevice.Impl;

import com.cajr.mapper.AdminMapper;
import com.cajr.sevice.AdminService;
import com.cajr.vo.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author CAJR
 * @date 2020/4/13 9:19 下午
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Integer addOneAdmin(Admin admin) {
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        admin.setStatus(1);
        admin.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        admin.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.adminMapper.insertSelective(admin);
    }

    @Override
    public Admin getInfo(String account) {
        return this.adminMapper.selectByAccount(account);
    }
}
