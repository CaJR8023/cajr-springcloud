package com.cajr.service.fallbak;

import com.cajr.service.ITagClientService;
import com.cajr.util.Result;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/3/16 11:21 上午
 */
@Component
public class ITagClientServiceFallBackFactory implements FallbackFactory<ITagClientService> {
    @Override
    public ITagClientService create(Throwable throwable) {
        return new ITagClientService() {
            @Override
            public Result addOneTag(Tag tag) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public Result addOneTag(ModuleTag moduleTag) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public Result addOneTag(NewsTag newsTag) {
                return new Result<>("hystrix fail",0);
            }
        };
    }
}