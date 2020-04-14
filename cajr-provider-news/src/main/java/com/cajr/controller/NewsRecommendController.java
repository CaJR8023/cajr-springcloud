package com.cajr.controller;

import com.cajr.service.NewsRecommendService;
import com.cajr.vo.news.CountNewsRecommendResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author CAJR
 * @date 2020/4/13 7:35 下午
 */
@RestController
@RequestMapping("/news_rec")
@Api(tags = "新闻推荐接口", value = "新闻推荐接口 restful api")
public class NewsRecommendController {

    @Autowired
    private NewsRecommendService newsRecommendService;

    @GetMapping("/count")
    public CountNewsRecommendResult countNewsRecommendResult(){
        List<Integer> cbs = new ArrayList<>();
        List<Integer> cfs = new ArrayList<>();
        List<Integer> hrs = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Random random = new Random();
            cbs.add(random.nextInt(10000));
            cfs.add(random.nextInt(10000));
            hrs.add(random.nextInt(10000));
        }
        return new CountNewsRecommendResult(cbs, cfs, hrs);
//        return this.newsRecommendService.countRecNewsNum();
    }
}
