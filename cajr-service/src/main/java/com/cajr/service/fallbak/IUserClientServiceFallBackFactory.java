package com.cajr.service.fallbak;

import com.cajr.service.IUserClientService;
import com.cajr.util.Result;
import com.cajr.vo.ImageResult;
import com.cajr.vo.SearchPage;
import com.cajr.vo.user.*;
import com.github.pagehelper.PageInfo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
            public Result getOneUserByTel(String tel) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public User getUser(int id) {
                User user = new User();
                user.setId(-1);
                return user;
            }

            @Override
            public Result addOneUser(User user) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public Result updateOneUser(User user) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public Result addOneUserNewsInit(User user) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public PageInfo getAllByPage(int page, int pageSize) {
                return null;
            }

            @Override
            public List<Integer> findAllUserId() {
                List<Integer> hystrixList = new ArrayList<>();
                hystrixList.add(-1);
                return hystrixList;
            }

            @Override
            public List<Integer> findActiveUserId() {
                List<Integer> hystrixList = new ArrayList<>();
                hystrixList.add(-1);
                return hystrixList;
            }

            @Override
            public UserOther getOneUserOther(int userId) {
                UserOther userOther = new UserOther();
                userOther.setId(-1);
                return userOther;
            }

            @Override
            public List<User> findSectionUserId(List<Integer> userIds) {
                List<User> hystrixList = new ArrayList<>();
                User user = new User();
                user.setId(0);
                hystrixList.add(user);
                return hystrixList;
            }

            @Override
            public Result addOne(UserPref userPref) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public Result updateUserPref(UserPref userPref) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public Result updateUserInfo(UserInfo userInfo) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public SearchPage searchUsers(String keyWord, int page, int pageSize) {
                return null;
            }

            @Override
            public ImageResult uploadAvatarImg(MultipartFile multipartFile) {
                return null;
            }

            @Override
            public Result star(UserNewsStar userNewsStar) {
                return null;
            }

            @Override
            public UserNewsStar getByUserIdAndNewsId(int userId, int newsId) {
                return null;
            }

            @Override
            public List<User> getFollowedUsers(Integer userId) {
                return null;
            }

            @Override
            public Result follow(Follow follow) {
                return null;
            }
        };
    }
}
