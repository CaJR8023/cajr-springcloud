package com.cajr.service.fallbak;

import com.cajr.service.INewsRecommendClientService;
import com.cajr.vo.news.CountNewsRecommendResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/4/14 1:00 下午
 */
@Component
public class INewsRecommendClientServiceFallBackFactory implements FallbackFactory<INewsRecommendClientService> {
    @Override
    public INewsRecommendClientService create(Throwable throwable) {
        return new INewsRecommendClientService() {
            @Override
            public CountNewsRecommendResult countNewsRecommendResult() {
                return null;
            }
        };
    }
}
