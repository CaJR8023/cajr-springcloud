package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.IUserLikeReviewClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.user.UserLikeReview;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:49 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = IUserLikeReviewClientServiceFallBackFactory.class)
public interface IUserLikeReviewClientService {
    @GetMapping("/user_like_review/review")
    public List<UserLikeReview> getByReviewId(@RequestParam("reviewId") int reviewId);

    @PostMapping("/user_like_review/")
    public Result addOne(@RequestBody UserLikeReview userLikeReview);

    @PutMapping("/user_like_review/")
    public Result updateOne(@RequestBody UserLikeReview userLikeReview);
}
