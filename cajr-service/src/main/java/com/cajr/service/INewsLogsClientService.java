package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.INewsLogsClientServiceFallBackFactory;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CAJR
 * @date 2020/4/14 4:33 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = INewsLogsClientServiceFallBackFactory.class)
public interface INewsLogsClientService {

    @GetMapping("/news_logs/")
    public PageInfo getAllByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);

}
