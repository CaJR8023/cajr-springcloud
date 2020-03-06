package com.cajr.controller;

import com.cajr.service.IUserClientService;
import com.cajr.service.NewsLogsService;
import com.cajr.service.RecommendService;
import com.cajr.util.Result;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.NewsLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @author CAJR
 * @date 2020/3/6 4:58 下午
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IUserClientService iUserClientService;

    @Autowired
    private NewsLogsService newsLogsService;

    @Autowired
    private RecommendService hotNewsRecommend;

    @Autowired
    private RecommendService contentBasedRecommend;
    @Autowired
    private RecommendService userCFRecommendImpl;



    @GetMapping("/")
    public Result test(){
        List<Integer> userIds = this.iUserClientService.findAllUserId();
//        hotNewsRecommend.recommend(userIds);
        contentBasedRecommend.recommend(userIds);
        return new Result<>(1);
    }

}
