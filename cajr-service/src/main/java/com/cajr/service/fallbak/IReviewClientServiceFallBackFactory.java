package com.cajr.service.fallbak;

import com.cajr.service.IReviewClientService;
import com.cajr.util.Result;
import com.cajr.vo.news.Review;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/4/21 3:20 下午
 */
@Component
public class IReviewClientServiceFallBackFactory implements FallbackFactory<IReviewClientService> {
    @Override
    public IReviewClientService create(Throwable throwable) {
        return new IReviewClientService() {

            @Override
            public Result getReviewsByNewsId(Integer newsId) {
                return new Result<>("hystrix", -1);
            }

            @Override
            public Result getReviewsByUserId(Integer userId) {
                return new Result<>("hystrix", -1);
            }

            @Override
            public Result addOneReview(Review review) {
                return new Result<>("hystrix", -1);
            }
        };
    }
}
