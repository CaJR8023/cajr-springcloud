package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.INewsRecommendClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.news.CountNewsRecommendResult;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CAJR
 * @date 2020/4/14 12:59 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = INewsRecommendClientServiceFallBackFactory.class)
public interface INewsRecommendClientService {

    @GetMapping("/news_rec/count")
    public CountNewsRecommendResult countNewsRecommendResult();

    @GetMapping("/news_rec/rec")
    public PageInfo getRecNewsList(@RequestParam("userId") Integer userId,
                                   @RequestParam(value = "page",defaultValue = "1") int page,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);

    @GetMapping("/news_rec/user_rec")
    public Result userRec(@RequestParam("userId") Integer userId);
}
