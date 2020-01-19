package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.ICodeClientServiceFallBackFactory;
import com.cajr.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CAJR
 * @date 2020/1/10 3:23 下午
 */
@FeignClient(name = "cajr-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = ICodeClientServiceFallBackFactory.class)
public interface ICodeClientService {
    @GetMapping("/code/send")
    public Result sendCode(@RequestParam("code") String code,
                           @RequestParam("phone") String phone);
}