package com.cajr.controller;

import com.cajr.service.NewsHotAndNewestService;
import com.cajr.service.NewsService;
import com.cajr.util.Result;
import com.cajr.vo.SearchPage;
import com.cajr.vo.news.News;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/4/6 11:10 下午
 */
@RestController
@RequestMapping("/news")
@Api(tags = "新闻接口",value = "新闻 rest接口")
public class NewsController {
    @Autowired
    NewsService newsService;

    @Autowired
    NewsHotAndNewestService newsHotAndNewestService;

    @GetMapping("/newest")
    public PageInfo getNewestNews(@RequestParam(value = "page",defaultValue = "1") int page,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.newsHotAndNewestService.newestNewsExtract(page, pageSize);
    }

    @GetMapping("/")
    public PageInfo getAll(@RequestParam(value = "page",defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.newsService.findAllByPage(page, pageSize);
    }

    @GetMapping("/{id}")
    public News getOne(@PathVariable("id") int id){
        return this.newsService.getOne(id);
    }

    @GetMapping("/undistributed")
    public News getUndistributedOne(@RequestParam("userId") int userId){
        return this.newsService.getUndistributedOne(userId);
    }

    @PostMapping("/")
    public Result addOne(@RequestBody News news){
        if (this.newsService.checkExistedByUserId(news.getUserId()) > 0){
            return new Result<>(0);
        }
        return new Result<>(this.newsService.add(news));
    }

    @PutMapping("/")
    public Result updateOne(@RequestBody News news){
        return new Result<>(this.newsService.update(news));
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable("id") int id){
        return new Result<>(this.newsService.deleteOne(id));
    }

    @GetMapping("/hot_24_hours")
    public Result get24HoursNews(){
        return new Result<>(this.newsHotAndNewestService.get24HoursHotNews());
    }

    @GetMapping("/search")
    public SearchPage searchNews(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "page",defaultValue = "1") int page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.newsService.searchNews(keyWord, page, pageSize);
    }

    @GetMapping("/distributed")
    public Result distributed(@RequestParam("id") int id){
        return new Result<>(this.newsService.distributedNews(id));
    }

    @GetMapping("/module")
    public Result getListByModuleId(@RequestParam("moduleId") int moduleId){
        return new Result<>(this.newsService.findAllByModuleId(moduleId));
    }

    @GetMapping("/my")
    public Result getMyNews(@RequestParam("userId") int userId){
        return new Result<>(this.newsService.findAllByUserId(userId));
    }

}
