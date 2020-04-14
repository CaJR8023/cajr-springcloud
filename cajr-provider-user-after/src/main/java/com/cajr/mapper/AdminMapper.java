package com.cajr.mapper;

import com.cajr.vo.admin.Admin;

/**
 * @author CAJR
 * @date 2019/11/25 8:01 下午
 */
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    Admin selectByAccount(String account);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}