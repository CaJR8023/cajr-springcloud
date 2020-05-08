package com.cajr.service;

import com.cajr.vo.user.Follow;
import com.cajr.vo.user.User;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/5/8 1:22 下午
 */
public interface FollowService {

    List<User> getFollowUsers(int userId);

    int follow(Follow follow);
}
