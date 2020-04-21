package com.cajr.service.impl;

import com.cajr.mapper.UserLikeReviewMapper;
import com.cajr.service.UserLikeReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CAJR
 * @date 2020/4/21 5:28 下午
 */
@Service
public class UserLikeReviewServiceImpl implements UserLikeReviewService {
    @Autowired
    private UserLikeReviewMapper userLikeReviewMapper;
}
