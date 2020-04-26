package com.cajr.service.fallbak;

import com.cajr.service.IUserLikeReviewClientService;
import com.cajr.util.Result;
import com.cajr.vo.user.UserLikeReview;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:51 下午
 */
@Component
public class IUserLikeReviewClientServiceFallBackFactory implements FallbackFactory<IUserLikeReviewClientService> {
    @Override
    public IUserLikeReviewClientService create(Throwable throwable) {
        return new IUserLikeReviewClientService() {
            @Override
            public List<UserLikeReview> getByReviewId(int reviewId) {
                return null;
            }

            @Override
            public Result addOne(UserLikeReview userLikeReview) {
                return new Result<>("hystrix fail",-1);
            }

            @Override
            public Result updateOne(UserLikeReview userLikeReview) {
                return new Result<>("hystrix fail",-1);
            }
        };
    }
}
