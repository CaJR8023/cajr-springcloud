package com.cajr.mapper;

import com.cajr.vo.user.Follow;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/5/8 1:21 下午
 */
public interface FollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);

    List<Follow> selectAll(int userId);

    int checkExisted(Follow record);

    int countFollowNum(int userId);

    int countFollowedNum(int followedId);

}