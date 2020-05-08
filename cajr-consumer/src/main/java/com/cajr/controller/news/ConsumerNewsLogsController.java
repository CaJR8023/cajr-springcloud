package com.cajr.controller.news;

import com.cajr.service.INewsLogsClientService;
import com.cajr.util.Result;
import com.cajr.vo.news.NewsLogs;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author CAJR
 * @date 2020/4/14 4:36 下午
 */
@RestController
@Validated
@RequestMapping("/news_logs")
@Api(tags = "新闻浏览记录接口",value = "新闻浏览记录 rest接口")
public class ConsumerNewsLogsController {

    @Autowired
    private INewsLogsClientService iNewsLogsClientService;

    @GetMapping("/")
    public PageInfo getAllByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.iNewsLogsClientService.getAllByPage(page, pageSize);
    }

    @PostMapping("/")
    public Result addNewsLogs(@RequestBody @NotNull NewsLogs newsLogs){
        return this.iNewsLogsClientService.addNewsLogs(newsLogs);
    }

    @GetMapping("/user")
    public Result getAllByUserId(@RequestParam("userId") int userId){
        return this.iNewsLogsClientService.getAllByUserId(userId);
    }
}
