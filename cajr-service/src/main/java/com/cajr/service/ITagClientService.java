package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.ITagClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/16 11:20 上午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = ITagClientServiceFallBackFactory.class)
public interface ITagClientService {

    @GetMapping("/tag/name")
    public Tag getOneByName(@RequestParam("name") String name);

    @GetMapping("/tag/{id}")
    public Tag getOneById(@PathVariable("id") int id);

    @PostMapping("/tag/")
    public Result addOneTag(@RequestBody Tag tag);

    @GetMapping("/tag/")
    public PageInfo getAllTag(@RequestParam(value = "page",defaultValue = "1") int page,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);

    @GetMapping("/tag_news/count_hot_tag")
    public Result countHottestTag();

    @GetMapping("/tag/hottest")
    public Result getHottestTags();

    @GetMapping("/tag_news/news")
    public List<Tag> getNewsTag(@RequestParam("newsId") Integer newsId);
}
