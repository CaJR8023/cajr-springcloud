package com.cajr.controller;

import com.cajr.service.NewsLogsService;
import com.cajr.util.Result;
import com.cajr.vo.news.NewsLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author CAJR
 * @date 2020/4/6 10:02 下午
 */
@RestController
@RequestMapping("/news_logs")
public class NewsLogsController {
    @Autowired
    NewsLogsService newsLogsService;

    @GetMapping("/{userId}")
    public Result getAllByUserId(@PathVariable("userId") int userId){
        return new Result<>(this.newsLogsService.findAllByUserId(userId));
    }

    @PostMapping("/")
    public Result addNewsLogs(@RequestBody NewsLogs newsLogs){
        if (this.newsLogsService.checkExistByUserIdAndNewsId(newsLogs.getUserId(), newsLogs.getNewsId()) >0){
            return new Result<>("浏览记录已存在");
        }
        newsLogs.setViewTime(Timestamp.valueOf(LocalDateTime.now()));
        newsLogs.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        newsLogs.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return new Result<>(this.newsLogsService.add(newsLogs));
    }

    @DeleteMapping("/{id}")
    public Result deleteNewsLogs(@PathVariable("id") Integer id){
        return new Result<>(this.newsLogsService.delete(id));
    }
}
