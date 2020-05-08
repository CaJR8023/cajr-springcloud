package com.cajr.mapper;

import com.cajr.vo.user.UserInfo;

/**
 * @author CAJR
 * @date 2020/4/6 7:19 下午
 */
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int updateByUserId(UserInfo record);

    UserInfo selectByUserId(int userId);
}