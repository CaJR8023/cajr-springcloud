package com.cajr.service.fallbak;

import com.cajr.service.IUserClientService;
import com.cajr.util.Result;
import com.cajr.vo.user.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2019/11/26 6:11 下午
 */
@Component
public class IUserClientServiceFallBackFactory implements FallbackFactory<IUserClientService> {
    @Override
    public IUserClientService create(Throwable throwable) {
        return new IUserClientService() {
            @Override
            public Result getOneUser(int id) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public Result addOneUser(User user) {
                return new Result<>("hystrix fail",0);
            }
        };
    }
}
