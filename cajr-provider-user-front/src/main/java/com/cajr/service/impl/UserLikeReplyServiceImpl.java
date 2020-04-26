package com.cajr.service.impl;

import com.cajr.mapper.UserLikeReplyMapper;
import com.cajr.service.UserLikeReplyService;
import com.cajr.vo.user.UserLikeReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:29 下午
 */
@Service
public class UserLikeReplyServiceImpl implements UserLikeReplyService {

    @Autowired
    private UserLikeReplyMapper userLikeReplyMapper;


    @Override
    public List<UserLikeReply> getAllByReplyId(int replyId) {
        return this.userLikeReplyMapper.selectAllByReplyId(replyId);
    }

    @Override
    public Integer add(UserLikeReply userLikeReply) {
        userLikeReply.setStatus(1);
        userLikeReply.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userLikeReply.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userLikeReplyMapper.insertSelective(userLikeReply);
    }

    @Override
    public Integer update(UserLikeReply userLikeReply) {
        userLikeReply.setStatus(1);
        userLikeReply.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.userLikeReplyMapper.updateByPrimaryKeySelective(userLikeReply);
    }
}
