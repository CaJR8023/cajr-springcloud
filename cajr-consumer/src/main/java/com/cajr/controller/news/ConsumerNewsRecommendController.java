package com.cajr.controller.news;

import com.cajr.service.INewsRecommendClientService;
import com.cajr.util.Result;
import com.cajr.vo.news.CountNewsRecommendResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author CAJR
 * @date 2020/4/14 1:01 下午
 */
@RestController
@Validated
@RequestMapping("/news_rec")
@Api(tags = "新闻推荐接口", value = "新闻推荐接口 restful api")
public class ConsumerNewsRecommendController {

    @Autowired
    private INewsRecommendClientService iNewsRecommendClientService;

    @GetMapping("/count")
    public CountNewsRecommendResult countNewsRecommendResult(){
        return this.iNewsRecommendClientService.countNewsRecommendResult();
    }

    @GetMapping("/rec")
    public PageInfo getRecNewsList(@RequestParam("userId") @NotNull @Min(1) Integer userId,
                                   @RequestParam(value = "page",defaultValue = "1") int page,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.iNewsRecommendClientService.getRecNewsList(userId, page, pageSize);
    }

    @GetMapping("/user_rec")
    public Result userRec(@RequestParam("userId") @NotNull @Min(1) Integer userId){
        return this.iNewsRecommendClientService.userRec(userId);
    }
}
