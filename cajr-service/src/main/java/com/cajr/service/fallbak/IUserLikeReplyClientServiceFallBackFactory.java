package com.cajr.service.fallbak;

import com.cajr.service.IUserLikeReplyClientService;
import com.cajr.util.Result;
import com.cajr.vo.user.UserLikeReply;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 5:50 下午
 */
@Component
public class IUserLikeReplyClientServiceFallBackFactory implements FallbackFactory<IUserLikeReplyClientService> {
    @Override
    public IUserLikeReplyClientService create(Throwable throwable) {
        return new IUserLikeReplyClientService() {
            @Override
            public List<UserLikeReply> getAllUserLikeReplyByReplyId(Integer replyId) {
                return null;
            }

            @Override
            public Result addOne(UserLikeReply userLikeReply) {
                return new Result<>("hystrix fail",-1);
            }

            @Override
            public Result updateOne(UserLikeReply userLikeReply) {
                return new Result<>("hystrix fail",-1);
            }
        };
    }
}
