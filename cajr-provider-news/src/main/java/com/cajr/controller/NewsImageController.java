package com.cajr.controller;

import com.cajr.service.NewsImageService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/4/14 4:02 下午
 */
@RestController
@RequestMapping("/news_img")
@Api(tags = "新闻图片 api",value = "新闻图片 rest接口")
public class NewsImageController {

    @Autowired
    private NewsImageService newsImageService;

    @GetMapping("/")
    public PageInfo getAllByPage(@RequestParam("page") int page,
                                 @RequestParam("pageSize") int pageSize){
        return this.newsImageService.getAllByPage(page, pageSize);
    }
}
