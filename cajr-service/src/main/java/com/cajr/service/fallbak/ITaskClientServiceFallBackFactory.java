package com.cajr.service.fallbak;

import com.cajr.service.ITaskClientService;
import com.cajr.util.Result;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/4/13 7:12 下午
 */
@Component
public class ITaskClientServiceFallBackFactory implements FallbackFactory<ITaskClientService> {
    @Override
    public ITaskClientService create(Throwable throwable) {
        return new ITaskClientService() {
            @Override
            public Result startRecommend(int isStart) {
                return new Result<>("hystrix", -1);
            }

            @Override
            public Result startCrawlNewsData(int isStart) {
                return new Result<>("hystrix", -1);
            }

            @Override
            public Result getRunTimeJob() {
                return new Result<>("hystrix", -1);
            }
        };
    }
}
