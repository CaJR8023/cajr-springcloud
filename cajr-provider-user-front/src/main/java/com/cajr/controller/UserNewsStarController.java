package com.cajr.controller;

import com.cajr.service.UserNewsStarService;
import com.cajr.util.Result;
import com.cajr.vo.user.UserNewsStar;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/5/8 1:50 下午
 */
@RestController
@RequestMapping("/user_news_star")
@Api(tags = "用户接口",value = "提供增删改查 rest接口")
public class UserNewsStarController {

    @Autowired
    private UserNewsStarService userNewsStarService;

    @PostMapping("/star")
    public Result star(@RequestBody UserNewsStar userNewsStar){
        return new Result<>(this.userNewsStarService.star(userNewsStar));
    }

    @GetMapping("/")
    public UserNewsStar getByUserIdAndNewsId(@RequestParam("userId") int userId,
                                             @RequestParam("newsId") int newsId){
        return this.userNewsStarService.getByUserIdAndNews(userId, newsId);
    }

}
