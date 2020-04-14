package com.cajr.controller.news;

import com.cajr.service.INewsImageClientService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/4/14 4:13 下午
 */
@RestController
@RequestMapping("/news_img")
@Api(tags = "新闻图片接口",value = "新闻图片 rest接口")
public class ConsumerNewsImageController {
    @Autowired
    private INewsImageClientService iNewsImageClientService;

    @GetMapping("/")
    public PageInfo getAllByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        return this.iNewsImageClientService.getAllByPage(page, pageSize);
    }
}
