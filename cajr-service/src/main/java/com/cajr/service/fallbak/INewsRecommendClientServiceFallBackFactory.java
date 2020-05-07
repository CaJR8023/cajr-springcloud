package com.cajr.service.fallbak;

import com.cajr.service.INewsRecommendClientService;
import com.cajr.util.Result;
import com.cajr.vo.news.CountNewsRecommendResult;
import com.github.pagehelper.PageInfo;
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

            @Override
            public PageInfo getRecNewsList(Integer userId, int page, int pageSize) {
                return null;
            }

            @Override
            public Result userRec(Integer userId) {
                return null;
            }
        };
    }
}
