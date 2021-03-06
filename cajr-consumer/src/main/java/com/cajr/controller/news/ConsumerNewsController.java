package com.cajr.controller.news;

import com.cajr.service.INewsClientService;
import com.cajr.util.Result;
import com.cajr.vo.SearchPage;
import com.cajr.vo.news.News;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/4/13 7:15 下午
 */
@RestController
@RequestMapping("/news")
@Api(tags = "新闻接口",value = "新闻 rest接口")
public class ConsumerNewsController {

    @Autowired
    private INewsClientService iNewsClientService;

    @GetMapping("/")
    public PageInfo getAll(@RequestParam(value = "page",defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.iNewsClientService.getAll(page, pageSize);
    }

    @GetMapping("/{id}")
    public News getOne(@PathVariable("id") int id){
        return this.iNewsClientService.getOne(id);
    }

    @PostMapping("/")
    public Result addOne(@RequestBody News news){
        if (news.getId() > 0){
            return updateOne(news);
        }
        return this.iNewsClientService.addOne(news);
    }

    @PutMapping("/")
    public Result updateOne(@RequestBody News news){
        return this.iNewsClientService.updateOne(news);
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable("id") int id){
        return this.iNewsClientService.deleteOne(id);
    }

    @GetMapping("/hot_24_hours")
    public Result get24HoursNews(){
        return this.iNewsClientService.get24HoursNews();
    }

    @GetMapping("/search")
    public SearchPage searchNews(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "page",defaultValue = "1") int page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.iNewsClientService.searchNews(keyWord, page, pageSize);
    }
    @GetMapping("/distributed")
    public Result distributed(@RequestParam("id") int id){
        return this.iNewsClientService.distributed(id);
    }

    @GetMapping("/module")
    public Result getListByModuleId(@RequestParam("moduleId") int moduleId){
        return this.iNewsClientService.getListByModuleId(moduleId);
    }

    @GetMapping("/my")
    public Result getMyNews(@RequestParam("userId") int userId){
        return this.iNewsClientService.getMyNews(userId);
    }
}
