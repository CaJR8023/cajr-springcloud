package com.cajr.service;

import com.cajr.vo.news.Reply;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 1:20 下午
 */
public interface ReplyService {
    List<Reply> getAllByReviewId(Integer reviewId);

    int addOneReply(Reply reply);
}
