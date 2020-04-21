package com.cajr.service;

import com.cajr.vo.news.Review;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 1:20 下午
 */
public interface ReviewService {

    Integer getReviewCountByNewsId(Integer newsId);

    Integer addOneReview(Review review);

    List<Review> getReviewsByNewsId(Integer newsId);

    List<Review> getReviewsByUserId(Integer userId);
}
