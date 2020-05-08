package com.cajr.controller;

import com.cajr.service.FollowService;
import com.cajr.util.Result;
import com.cajr.vo.user.Follow;
import com.cajr.vo.user.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/5/8 1:22 下午
 */
@RestController
@RequestMapping("/follow")
@Api(tags = "用户关注接口",value = "提供增删改查 rest接口")
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/followed_users")
    public List<User> getFollowedUsers(@RequestParam("userId") Integer userId){
        return this.followService.getFollowUsers(userId);
    }

    @PostMapping("/")
    public Result follow(@RequestBody Follow follow){
        return new Result<>(this.followService.follow(follow));
    }

}
