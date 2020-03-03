package com.cajr.mapper;

import com.cajr.vo.user.UserLog;

/**
 * @author CAJR
 * @date 2020/3/3 11:45 上午
 */
public interface UserLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByUserIdSelective(UserLog record);

    int updateByPrimaryKey(UserLog record);

    UserLog selectOneByUserId(int userId);
}