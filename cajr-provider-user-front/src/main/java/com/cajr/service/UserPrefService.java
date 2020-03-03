package com.cajr.service;

import com.cajr.vo.user.UserPref;

/**
 * @author CAJR
 * @date 2020/3/3 3:16 下午
 */
public interface UserPrefService {
    public Integer add(UserPref userPref);

    public Integer update(UserPref userPref);
}
