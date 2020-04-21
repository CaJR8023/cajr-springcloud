package com.cajr.service.fallbak;

import com.cajr.service.INewsClientService;
import com.cajr.util.Result;
import com.cajr.vo.news.News;
import com.github.pagehelper.PageInfo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/4/13 7:07 下午
 */
@Component
public class INewsClientServiceFallBackFactory implements FallbackFactory<INewsClientService> {
    @Override
    public INewsClientService create(Throwable throwable) {
        return new INewsClientService() {
            @Override
            public PageInfo getNewestNews(int page, int pageSize) {
                return null;
            }

            @Override
            public PageInfo getAll(int page, int pageSize) {
                return null;
            }

            @Override
            public News getOne(int id) {
                News news = new News();
                news.setId(0);
                return news;
            }

            @Override
            public Result addOne(News news) {
                return new Result<>("hystrix", -1);
            }

            @Override
            public Result updateOne(News news) {
                return new Result<>("hystrix", -1);
            }

            @Override
            public Result deleteOne(int id) {
                return new Result<>("hystrix", -1);
            }

            @Override
            public Result get24HoursNews() {
                return new Result<>("hystrix", -1);
            }
        };
    }
}
