package com.cajr.service.fallbak;

import com.cajr.service.ICodeClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/1/10 3:23 下午
 */
@Component
public class ICodeClientServiceFallBackFactory implements FallbackFactory<ICodeClientService> {
    @Override
    public ICodeClientService create(Throwable throwable) {
        return null;
    }
}
