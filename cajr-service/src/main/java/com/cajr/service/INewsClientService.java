package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.INewsClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.news.News;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
