package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.INewsRecommendClientServiceFallBackFactory;
import com.cajr.vo.news.CountNewsRecommendResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CAJR
 * @date 2020/4/14 12:59 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = INewsRecommendClientServiceFallBackFactory.class)
public interface INewsRecommendClientService {

    @GetMapping("/news_rec/count")
    public CountNewsRecommendResult countNewsRecommendResult();
}
