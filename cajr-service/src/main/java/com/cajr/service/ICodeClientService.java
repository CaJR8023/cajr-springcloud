package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.ICodeClientServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author CAJR
 * @date 2020/1/10 3:23 下午
 */
@FeignClient(name = "cajr-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = ICodeClientServiceFallBackFactory.class)
public interface ICodeClientService {
}
