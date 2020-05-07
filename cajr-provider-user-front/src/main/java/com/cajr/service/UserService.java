package com.cajr.service;

import com.cajr.util.CustomHashMap;
import com.cajr.vo.SearchPage;
import com.cajr.vo.user.User;
import com.cajr.vo.user.UserOther;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @Author CAJR
 * @create 2019/11/23 2:21
 */
public interface UserService {

    public Optional<User> getOneUser(int id);

    UserOther getOneUserOther(int id);

    public User getUser(int id);

    public User getUserByTel(String tel);

    public User getUserByUserName(String userName);

    public Optional<Integer> add(User user);

    public Integer addNewsInit(User user);

    public Optional<Integer> update(User user);

    public Optional<Integer> deleteByStatus(int id);

    public Optional<Integer> checkIsExistByTel(String tel);

    public Optional<Integer> checkIsExistByUserName(String userName);

    public Optional<Integer> checkIsExistById(int id);

    public List<Integer> findAllUserId();

    public List<User> findSection(List<Integer> userIds);

    public List<Integer> findAllActiveUserId();

    PageInfo getAllByPage(int page, int pageSize);

    SearchPage search(String keyWord, int pageNum, int pageSize);

}
