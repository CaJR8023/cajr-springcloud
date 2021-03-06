package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.INewsClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.ImageResult;
import com.cajr.vo.SearchPage;
import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsUser;
import com.cajr.vo.news.Reply;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CAJR
 * @date 2020/4/13 7:07 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = INewsClientServiceFallBackFactory.class)
public interface INewsClientService {
    @GetMapping("/news/newest")
    public PageInfo getNewestNews(@RequestParam(value = "page",defaultValue = "1") int page,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);

    @GetMapping("/news/")
    public PageInfo getAll(@RequestParam(value = "page",defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);

    @GetMapping("/news/{id}")
    public News getOne(@PathVariable("id") int id);

    @PostMapping("/news/")
    public Result addOne(@RequestBody News news);

    @PutMapping("/news/")
    public Result updateOne(@RequestBody News news);

    @DeleteMapping("/news/{id}")
    public Result deleteOne(@PathVariable("id") int id);

    @GetMapping("/news/hot_24_hours")
    public Result get24HoursNews();

    @GetMapping("/news/search")
    public SearchPage searchNews(@RequestParam("keyWord") String keyWord,
                                  @RequestParam(value = "page",defaultValue = "1") int page,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);

    @GetMapping("/news/undistributed")
    public News getUndistributedOne(@RequestParam("userId") int userId);

    @GetMapping("/news/distributed")
    public Result distributed(@RequestParam("id") int id);

    @GetMapping("/news/module")
    public Result getListByModuleId(@RequestParam("moduleId") int moduleId);

    @GetMapping("/news/my")
    public Result getMyNews(@RequestParam("userId") int userId);

    @PostMapping("/img/news/upload")
    public ImageResult uploadNewsImg(@RequestParam("file") MultipartFile multipartFile);

    @GetMapping("/collect/news")
    public Result getCollectNewsList(@RequestParam("userId") int userId);

    @PostMapping("/collect/")
    public Result collect(@RequestBody NewsUser newsUser);

    @PostMapping("/reply/")
    public Result addOneReply(@RequestBody Reply reply);

}
