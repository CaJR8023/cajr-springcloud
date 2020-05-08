package com.cajr.controller;

import com.cajr.mapper.NewsUserMapper;
import com.cajr.service.NewsUserService;
import com.cajr.util.Result;
import com.cajr.vo.news.NewsUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/5/8 4:00 下午
 */
@RestController
@RequestMapping("/collect")
@Api(tags = "新闻接口",value = "新闻 rest接口")
public class CollectController {
    @Autowired
    private NewsUserService newsUserService;

    @GetMapping("/news")
    public Result getCollectNewsList(@RequestParam("userId") int userId){
        return new Result<>(this.newsUserService.getByUserId(userId));
    }

    @PostMapping("/")
    public Result collect(@RequestBody NewsUser newsUser){
        return new Result<>(this.newsUserService.collect(newsUser));
    }
}
