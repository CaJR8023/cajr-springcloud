package com.cajr.service.impl;

import com.cajr.mapper.ReviewMapper;
import com.cajr.service.IUserClientService;
import com.cajr.service.ReviewService;
import com.cajr.vo.news.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 1:34 下午
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private IUserClientService iUserClientService;

    @Override
    public Integer getReviewCountByNewsId(Integer newsId) {
        return this.reviewMapper.findNumByNewsId(newsId);
    }

    @Override
    public Integer addOneReview(Review review) {
        return this.reviewMapper.insertSelective(review);
    }

    @Override
    public List<Review> getReviewsByNewsId(Integer newsId) {
        List<Review> reviews = this.reviewMapper.selectAllByNewsId(newsId);
        if (!reviews.isEmpty()){

        }
        return reviews;
    }

    @Override
    public List<Review> getReviewsByUserId(Integer userId) {
        return this.reviewMapper.selectAllByUserId(userId);
    }
}
