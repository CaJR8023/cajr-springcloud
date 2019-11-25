package com.cajr.mapper;

import com.cajr.vo.permission.UserRole;

/**
 * @author CAJR
 * @date 2019/11/25 7:35 下午
 */
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}