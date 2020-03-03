package com.cajr.service;

import com.cajr.vo.user.UserLog;

/**
 * @author CAJR
 * @date 2020/3/3 11:47 上午
 */
public interface UserLogService {

    UserLog getByUserId(int userId);

    Integer add(UserLog userLog);

    Integer updateUserId(UserLog userLog);
}
