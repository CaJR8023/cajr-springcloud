package com.cajr.service.impl;

import com.cajr.mapper.UserLikeReplyMapper;
import com.cajr.service.UserLikeReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CAJR
 * @date 2020/4/21 5:29 下午
 */
@Service
public class UserLikeReplyServiceImpl implements UserLikeReplyService {

    @Autowired
    private UserLikeReplyMapper userLikeReplyMapper;


}
