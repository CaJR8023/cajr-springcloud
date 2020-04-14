package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.ITaskClientServiceFallBackFactory;
import com.cajr.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CAJR
 * @date 2020/4/13 7:12 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = ITaskClientServiceFallBackFactory.class)
public interface ITaskClientService {
    @GetMapping("/rec")
    public Result startRecommend(@RequestParam("isStart") int isStart);

    @GetMapping("/news_data")
    public Result startCrawlNewsData(@RequestParam("isStart") int isStart);

    @GetMapping("/runtime_job")
    public Result getRunTimeJob();
}
