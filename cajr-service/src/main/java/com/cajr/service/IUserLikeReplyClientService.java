package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.IUserLikeReplyClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.user.UserLikeReply;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:50 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = IUserLikeReplyClientServiceFallBackFactory.class)
public interface IUserLikeReplyClientService {
    @GetMapping("/user_like_reply/reply_like_num")
    public List<UserLikeReply> getAllUserLikeReplyByReplyId(@RequestParam("replyId") Integer replyId);

    @PostMapping("/user_like_reply/")
    public Result addOne(@RequestBody UserLikeReply userLikeReply);

    @PutMapping("/user_like_reply/")
    public Result updateOne(@RequestBody UserLikeReply userLikeReply);
}
