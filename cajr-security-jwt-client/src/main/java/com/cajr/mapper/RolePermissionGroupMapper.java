package com.cajr.mapper;

import com.cajr.vo.permission.RolePermissionGroup;

/**
 * @author CAJR
 * @date 2019/11/25 7:35 下午
 */
public interface RolePermissionGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermissionGroup record);

    int insertSelective(RolePermissionGroup record);

    RolePermissionGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermissionGroup record);

    int updateByPrimaryKey(RolePermissionGroup record);
}