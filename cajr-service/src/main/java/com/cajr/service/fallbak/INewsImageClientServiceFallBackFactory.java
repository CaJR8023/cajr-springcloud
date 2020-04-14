package com.cajr.service.fallbak;

import com.cajr.service.INewsImageClientService;
import com.github.pagehelper.PageInfo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/4/14 4:11 下午
 */
@Component
public class INewsImageClientServiceFallBackFactory implements FallbackFactory<INewsImageClientService> {
    @Override
    public INewsImageClientService create(Throwable throwable) {
        return new INewsImageClientService() {
            @Override
            public PageInfo getAllByPage(int page, int pageSize) {
                return null;
            }
        };
    }
}
