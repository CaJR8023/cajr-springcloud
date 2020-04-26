package com.cajr.service.impl;

import com.cajr.mapper.UserLikeReviewMapper;
import com.cajr.service.UserLikeReviewService;
import com.cajr.vo.user.UserLikeReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:28 下午
 */
@Service
public class UserLikeReviewServiceImpl implements UserLikeReviewService {
    @Autowired
    private UserLikeReviewMapper userLikeReviewMapper;

    @Override
    public List<UserLikeReview> getByReviewId(Integer reviewId) {
        return this.userLikeReviewMapper.selectByReviewId(reviewId);
    }

    @Override
    public Integer add(UserLikeReview userLikeReview) {
        userLikeReview.setStatus(1);
        userLikeReview.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userLikeReviewMapper.insertSelective(userLikeReview);
    }

    @Override
    public Integer update(UserLikeReview userLikeReview) {
        userLikeReview.setStatus(1);
        userLikeReview.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userLikeReviewMapper.updateByPrimaryKeySelective(userLikeReview);
    }
}
