package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.INewsImageClientServiceFallBackFactory;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CAJR
 * @date 2020/4/14 4:07 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = INewsImageClientServiceFallBackFactory.class)
public interface INewsImageClientService {

    @GetMapping("/news_img/")
    public PageInfo getAllByPage(@RequestParam("page") int page,
                                 @RequestParam("pageSize") int pageSize);
}
