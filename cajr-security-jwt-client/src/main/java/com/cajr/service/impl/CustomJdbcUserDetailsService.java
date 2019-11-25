package com.cajr.service.impl;

import com.cajr.vo.admin.Admin;
import com.cajr.vo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author CAJR
 * @date 2019/11/25 7:09 下午
 */
@Service
public class CustomJdbcUserDetailsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String USER_BASIC_COLUMN_LIST = "id,username,tel,password,status";
    private static final String ADMIN_BASIC_COLUMN_LIST = "id,account,password,status";

    private static final String GET_ONE_USER_BY_TEL_SQL = "select"+USER_BASIC_COLUMN_LIST+"from user where tel =?";
    private static final String GET_ONE_ADMIN_BY_TEL_SQL = "select"+ADMIN_BASIC_COLUMN_LIST+"from admin where tel =?";

    Optional<User> getOneUserByTel(String tel){
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user;
        try {
            user = jdbcTemplate.queryForObject(GET_ONE_USER_BY_TEL_SQL,rowMapper,tel);
        }catch (Exception e){
            user = new User();
        }

        return Optional.ofNullable(user);
    }

    Optional<Admin> getOneAdminByAccount(String account){
        RowMapper<Admin> rowMapper = new BeanPropertyRowMapper<>(Admin.class);
        Admin admin;
        try {
            admin = jdbcTemplate.queryForObject(GET_ONE_ADMIN_BY_TEL_SQL,rowMapper,account);
        }catch (Exception e){
            return Optional.empty();
        }

        return Optional.ofNullable(admin);
    }

}
