package com.cajr.service.fallbak;

import com.cajr.service.INewsLogsClientService;
import com.cajr.util.Result;
import com.cajr.vo.news.NewsLogs;
import com.github.pagehelper.PageInfo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/4/14 4:34 下午
 */
@Component
public class INewsLogsClientServiceFallBackFactory implements FallbackFactory<INewsLogsClientService> {
    @Override
    public INewsLogsClientService create(Throwable throwable) {
        return new INewsLogsClientService() {
            @Override
            public PageInfo getAllByPage(int page, int pageSize) {
                return null;
            }

            @Override
            public Result addNewsLogs(NewsLogs newsLogs) {
                return null;
            }
        };
    }
}
