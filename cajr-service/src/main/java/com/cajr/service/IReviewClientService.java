package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.IReviewClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.news.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CAJR
 * @date 2020/4/21 3:19 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = IReviewClientServiceFallBackFactory.class)
public interface IReviewClientService {

    @GetMapping("/review/news")
    public Result getReviewsByNewsId(@RequestParam("newsId") Integer newsId);

    @GetMapping("/review/user")
    public Result getReviewsByUserId(@RequestParam("userId") Integer userId);

    @PostMapping("/review/")
    public Result addOneReview(@RequestBody Review review);
}
