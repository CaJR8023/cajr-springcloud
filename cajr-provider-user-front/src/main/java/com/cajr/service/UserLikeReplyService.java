package com.cajr.service;

import com.cajr.vo.user.UserLikeReply;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:29 下午
 */
public interface UserLikeReplyService {
    List<UserLikeReply> getAllByReplyId(int replyId);

    Integer add(UserLikeReply userLikeReply);

    Integer update(UserLikeReply userLikeReply);
}
