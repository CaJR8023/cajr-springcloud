package com.cajr.controller.news;

import com.cajr.service.INewsClientService;
import com.cajr.util.Result;
import com.cajr.vo.news.NewsUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/5/8 6:14 下午
 */
@RestController
@RequestMapping("/collect")
@Api(tags = "收藏接口",value = "收藏 rest接口")
public class ConsumerCollectController {

    @Autowired
    private INewsClientService iNewsClientService;

    @GetMapping("/news")
    public Result getCollectNewsList(@RequestParam("userId") int userId){
        return this.iNewsClientService.getCollectNewsList(userId);
    }

    @PostMapping("/")
    public Result collect(@RequestBody NewsUser newsUser){
        return this.iNewsClientService.collect(newsUser);
    }
}
