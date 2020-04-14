package com.cajr.controller.news;

import com.cajr.service.INewsRecommendClientService;
import com.cajr.vo.news.CountNewsRecommendResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/4/14 1:01 下午
 */
@RestController
@RequestMapping("/news_rec")
@Api(tags = "新闻推荐接口", value = "新闻推荐接口 restful api")
public class ConsumerNewsRecommendController {

    @Autowired
    private INewsRecommendClientService iNewsRecommendClientService;

    @GetMapping("/count")
    public CountNewsRecommendResult countNewsRecommendResult(){
        return this.iNewsRecommendClientService.countNewsRecommendResult();
    }
}
