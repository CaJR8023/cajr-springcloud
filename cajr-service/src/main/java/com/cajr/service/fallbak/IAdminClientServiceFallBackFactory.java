package com.cajr.service.fallbak;

import com.cajr.service.IAdminClientService;
import com.cajr.util.Result;
import com.cajr.vo.admin.Admin;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/4/13 9:16 下午
 */
@Component
public class IAdminClientServiceFallBackFactory implements FallbackFactory<IAdminClientService> {
    @Override
    public IAdminClientService create(Throwable throwable) {
        return new IAdminClientService() {
            @Override
            public Result addOneAdmin(Admin admin) {
                return new Result<>("hystrix",-2);
            }

            @Override
            public Result getAdminInfo(String account) {
                return new Result<>("hystrix",-2);
            }
        };
    }
}
