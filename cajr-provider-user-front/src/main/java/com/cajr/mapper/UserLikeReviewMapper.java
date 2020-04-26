package com.cajr.mapper;

import com.cajr.vo.user.UserLikeReview;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/6 7:19 下午
 */
public interface UserLikeReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLikeReview record);

    int insertSelective(UserLikeReview record);

    UserLikeReview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLikeReview record);

    int updateByPrimaryKey(UserLikeReview record);

    List<UserLikeReview> selectByReviewId(Integer reviewId);
}