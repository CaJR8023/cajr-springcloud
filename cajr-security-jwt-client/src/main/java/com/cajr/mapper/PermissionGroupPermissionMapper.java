package com.cajr.mapper;

import com.cajr.vo.permission.PermissionGroupPermission;

/**
 * @author CAJR
 * @date 2019/11/25 7:34 下午
 */
public interface PermissionGroupPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionGroupPermission record);

    int insertSelective(PermissionGroupPermission record);

    PermissionGroupPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionGroupPermission record);

    int updateByPrimaryKey(PermissionGroupPermission record);
}