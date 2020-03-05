package com.cajr.mapper;

import com.cajr.vo.user.UserPref;

/**
 * @author CAJR
 * @date 2020/3/3 2:35 下午
 */
public interface UserPrefMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPref record);

    int insertSelective(UserPref record);

    UserPref selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPref record);

    int updateByUserIdSelective(UserPref record);

    int updateByPrimaryKey(UserPref record);

    UserPref selectByUserId(int userId);

    int checkExistUserPrefByUserId(Integer userId);
}