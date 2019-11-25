package com.cajr.mapper;

import com.cajr.vo.permission.PermissionGroup;

/**
 * @author CAJR
 * @date 2019/11/25 7:33 下午
 */
public interface PermissionGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionGroup record);

    int insertSelective(PermissionGroup record);

    PermissionGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionGroup record);

    int updateByPrimaryKey(PermissionGroup record);
}