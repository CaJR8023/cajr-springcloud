package com.cajr.controller.user;

import com.cajr.service.IUserClientService;
import com.cajr.util.Result;
import com.cajr.vo.user.Follow;
import com.cajr.vo.user.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/5/8 3:52 下午
 */
@RestController
@RequestMapping("/follow")
@Api(tags = "关注接口",value = "提供增删改查 rest接口")
public class ConsumerFollowController {

    @Autowired
    private IUserClientService iUserClientService;

    @GetMapping("/follow/followed_users")
    public List<User> getFollowedUsers(@RequestParam("userId") Integer userId){
        return this.iUserClientService.getFollowedUsers(userId);
    }

    @PostMapping("/follow/")
    public Result follow(@RequestBody Follow follow){
        return this.iUserClientService.follow(follow);
    }
}
