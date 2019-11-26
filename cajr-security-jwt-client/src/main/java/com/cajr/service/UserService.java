package com.cajr.service;

import com.cajr.vo.admin.Admin;
import com.cajr.vo.user.User;

import java.util.Optional;

/**
 * @author CAJR
 * @date 2019/11/26 12:34 下午
 */
public interface UserService {
    Optional<User> getOneUserByTel(String tel);

    Optional<Admin> getOneAdminByAccount(String account);
}
