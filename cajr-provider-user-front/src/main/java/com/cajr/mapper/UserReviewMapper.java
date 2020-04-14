package com.cajr.mapper;

import com.cajr.vo.user.UserReview;

/**
 * @author CAJR
 * @date 2020/4/6 7:19 下午
 */
public interface UserReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserReview record);

    int insertSelective(UserReview record);

    UserReview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserReview record);

    int updateByPrimaryKey(UserReview record);
}