package com.cajr.controller;

import com.cajr.service.NewsDataService;
import com.cajr.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/1/16 11:14 上午
 */
@RestController
@RequestMapping("/news_data")
@Api(tags = "新闻数据抓取",value = "新闻数据抓取 rest接口")
public class NewsDataController {
    @Autowired
    NewsDataService newsDataService;

    @GetMapping("/channel")
    public Result crawlChannelData() throws Exception {
        return new Result<>(this.newsDataService.crawlChannelData());
    }

    @GetMapping("/")
    public Result crawlNewsData() throws Exception {
        return new Result<>(this.newsDataService.crawlNewsData());
    }
}
