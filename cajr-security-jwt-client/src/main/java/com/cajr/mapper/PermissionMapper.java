package com.cajr.mapper;

import com.cajr.vo.permission.Permission;

/**
 * @author CAJR
 * @date 2019/11/25 7:33 下午
 */
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}