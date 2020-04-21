package com.cajr.service.impl;

import com.cajr.mapper.ReviewMapper;
import com.cajr.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CAJR
 * @date 2020/4/20 1:34 下午
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public Integer getReviewCountByNewsId(Integer newsId) {
        return null;
    }
}
