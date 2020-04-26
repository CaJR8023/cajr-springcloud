package com.cajr.service;

import com.cajr.vo.user.UserLikeReview;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:28 下午
 */
public interface UserLikeReviewService {
    List<UserLikeReview> getByReviewId(Integer reviewId);

    Integer add(UserLikeReview userLikeReview);

    Integer update(UserLikeReview userLikeReview);
}
