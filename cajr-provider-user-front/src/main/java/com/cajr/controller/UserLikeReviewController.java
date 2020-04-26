package com.cajr.controller;

import com.cajr.service.UserLikeReviewService;
import com.cajr.util.Result;
import com.cajr.vo.user.UserLikeReview;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:26 下午
 */
@RestController
@RequestMapping("/user_like_review")
@Api(tags = "用户评论接口",value = "提供增删改查 rest接口")
public class UserLikeReviewController {

    @Autowired
    private UserLikeReviewService userLikeReviewService;

    @GetMapping("/review")
    public List<UserLikeReview> getByReviewId(@RequestParam("reviewId") int reviewId){
        return this.userLikeReviewService.getByReviewId(reviewId);
    }

    @PostMapping("/")
    public Result addOne(@RequestBody UserLikeReview userLikeReview){
        return new Result<>(this.userLikeReviewService.add(userLikeReview));
    }

    @PutMapping("/")
    public Result updateOne(@RequestBody UserLikeReview userLikeReview){
        return new Result<>(this.userLikeReviewService.update(userLikeReview));
    }
}
