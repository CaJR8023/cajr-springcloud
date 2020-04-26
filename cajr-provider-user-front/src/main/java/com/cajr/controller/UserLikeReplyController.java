package com.cajr.controller;

import com.cajr.service.UserLikeReplyService;
import com.cajr.util.Result;
import com.cajr.vo.user.UserLikeReply;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:26 下午
 */
@RestController
@RequestMapping("/user_like_reply")
@Api(tags = "用户回复接口",value = "提供增删改查 rest接口")
public class UserLikeReplyController {

    @Autowired
    private UserLikeReplyService userLikeReplyService;

    @GetMapping("/reply_like_num")
    public List<UserLikeReply> getAllUserLikeReplyByReplyId(@RequestParam("replyId") Integer replyId){
        return this.userLikeReplyService.getAllByReplyId(replyId);
    }

    @PostMapping("/")
    public Result addOne(@RequestBody UserLikeReply userLikeReply){
        return new Result<>(this.userLikeReplyService.add(userLikeReply));
    }

    @PutMapping("/")
    public Result updateOne(@RequestBody UserLikeReply userLikeReply){
        return new Result<>(this.userLikeReplyService.update(userLikeReply));
    }
}
