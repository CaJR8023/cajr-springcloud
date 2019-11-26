package com.cajr.service;

import com.cajr.vo.user.User;

import java.util.Optional;

/**
 * @Author CAJR
 * @create 2019/11/23 2:21
 */
public interface UserService {

    public Optional<User> getOneUser(int id);

    public Optional<Integer> add(User user);

    public Optional<Integer> update(User user);

    public Optional<Integer> deleteByStatus(int id);

    public Optional<Integer> checkIsExistByTel(String tel);

    public Optional<Integer> checkIsExistById(int id);
}
