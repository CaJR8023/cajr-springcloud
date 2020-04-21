package com.cajr.controller;

import com.cajr.service.ReviewService;
import com.cajr.util.Result;
import com.cajr.vo.news.Review;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/4/21 2:46 下午
 */
@RestController
@RequestMapping("/review")
@Api(tags = "评论接口",value = "评论 rest接口")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/news")
    public Result getReviewsByNewsId(@RequestParam("newsId") Integer newsId){
        return new Result<>(this.reviewService.getReviewsByNewsId(newsId));
    }

    @GetMapping("/user")
    public Result getReviewsByUserId(@RequestParam("userId") Integer userId){
        return new Result<>(this.reviewService.getReviewsByUserId(userId));
    }

    @PostMapping("/")
    public Result addOneReview(@RequestBody Review review){
        return new Result<>(this.reviewService.addOneReview(review));
    }
}
