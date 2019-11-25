package com.cajr.mapper;

import com.cajr.vo.permission.Role;

/**
 * @author CAJR
 * @date 2019/11/25 7:34 下午
 */
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}