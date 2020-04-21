package com.cajr.controller.review;

import com.cajr.service.IReviewClientService;
import com.cajr.util.Result;
import com.cajr.vo.news.Review;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/4/21 3:23 下午
 */
@RestController
@RequestMapping("/review")
@Api(tags = "评论接口",value = "评论 rest接口")
public class ConsumerReviewController {

    @Autowired
    private IReviewClientService iReviewClientService;

    @GetMapping("/news")
    public Result getReviewsByNewsId(@RequestParam("newsId") Integer newsId){
        return this.iReviewClientService.getReviewsByNewsId(newsId);
    }

    @GetMapping("/user")
    public Result getReviewsByUserId(@RequestParam("userId") Integer userId){
        return this.iReviewClientService.getReviewsByUserId(userId);
    }

    @PostMapping("/")
    public Result addOneReview(@RequestBody Review review){
        return this.iReviewClientService.addOneReview(review);
    }
}
