package com.cajr.service.fallbak;

import com.cajr.service.IModuleService;
import com.cajr.vo.news.Module;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/10 12:19 下午
 */
@Component
public class IModuleServiceFallBackFactory implements FallbackFactory<IModuleService> {
    @Override
    public IModuleService create(Throwable throwable) {
        return new IModuleService() {

            @Override
            public List<Module> getAllModule() {
                List<Module> modules = new ArrayList<>();
                Module module = new Module();
                module.setId(-1);
                modules.add(module);
                return modules;
            }
        };
    }
}
