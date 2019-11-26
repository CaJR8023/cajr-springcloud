package com.cajr.mapper;

import com.cajr.vo.user.User;

/**
 * @author CAJR
 * @date 2019/11/26 11:54 上午
 */
public interface UserMapper {
    User findOneByTel(String tel);
}
