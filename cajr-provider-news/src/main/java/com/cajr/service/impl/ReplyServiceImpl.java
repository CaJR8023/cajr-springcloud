package com.cajr.service.impl;

import com.cajr.mapper.ReplyMapper;
import com.cajr.service.IUserClientService;
import com.cajr.service.IUserLikeReplyClientService;
import com.cajr.service.ReplyService;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.Reply;
import com.cajr.vo.user.UserLikeReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 1:34 下午
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private IUserClientService iUserClientService;

    @Autowired
    private IUserLikeReplyClientService iUserLikeReplyClientService;

    @Override
    public List<Reply> getAllByReviewId(Integer reviewId) {
        List<Reply> replies = this.replyMapper.selectAllByReviewId(reviewId);
        if (!replies.isEmpty()){
            replies.forEach(reply -> {
                List<UserLikeReply> userLikeReplies = this.iUserLikeReplyClientService.getAllUserLikeReplyByReplyId(reply.getId());
                if (!userLikeReplies.isEmpty()){
                    int likeNum = 0, unLikeNum = 0;
                    List<Integer> likeUserIds = new ArrayList<>();
                    List<Integer> unlikeUserIds = new ArrayList<>();
                    for (UserLikeReply userLikeReply : userLikeReplies) {
                        if (userLikeReply.getIsLike() == 1){
                            likeNum++;
                            likeUserIds.add(userLikeReply.getUserId());
                        }else {
                            unLikeNum ++;
                            unlikeUserIds.add(userLikeReply.getUserId());
                        }
                    }
                    reply.setLikeNum(likeNum);
                    reply.setUnlikeNum(unLikeNum);
                    reply.setLikeUserIds(likeUserIds);
                    reply.setUnlikeUserIds(unlikeUserIds);
                }else {
                    reply.setLikeNum(0);
                    reply.setUnlikeNum(0);
                }
                reply.setTime(TimeUtil.format(reply.getCreatedAt()));
                reply.setUserOther(this.iUserClientService.getOneUserOther(reply.getUserId()));
                reply.setRepliedUserOther(this.iUserClientService.getOneUserOther(reply.getRepliedUserId()));
            });
        }
        return replies;
    }
}
