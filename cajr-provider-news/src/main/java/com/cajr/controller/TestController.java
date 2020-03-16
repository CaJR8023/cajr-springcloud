package com.cajr.controller;

import com.cajr.job.TestJob;
import com.cajr.service.*;
import com.cajr.util.Result;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.NewsLogs;
import com.cajr.vo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private UserPrefRefresherService userPrefRefresherService;

    @Autowired
    private QuartzService quartzService;




    @GetMapping("/user")
    public Result testUser(){

        for (int i = 1; i < 100; i++) {
            User user = new User();
            if (i < 9){
                user.setTel("1511202200"+i);
            }else {
                user.setTel("151120220"+i);
            }
            user.setUsername("cajr-"+i);
            user.setPassword("20180615");
            this.iUserClientService.addOneUser(user);
        }

//        List<Integer> userIds = this.iUserClientService.findAllUserId();
//        hotNewsRecommend.recommend(userIds);
//        contentBasedRecommend.recommend(userIds);
//        userPrefRefresherService.refresh();
//        userCFRecommendImpl.recommend(userIds);

        return new Result<>(1);
    }


    @GetMapping("/user_log")
    public Result testUserLog(){
        List<Integer> userIds = this.iUserClientService.findAllUserId();

        for (Integer userId : userIds) {
            int count = 10;
            while (count > 0){
                NewsLogs newsLogs = new NewsLogs();
                newsLogs.setUserId(userId);
                newsLogs.setStatus(1);
                newsLogs.setNewsId((int) (Math.random()*(49919-122)+122));
                newsLogs.setViewTime(TimeUtil.getInRecTimestamp((int) (Math.random()*(3-1)+1)));
                newsLogs.setPreferDegree((int) (Math.random()*(2)+0));
                this.newsLogsService.add(newsLogs);
                count--;
            }
        }
//        hotNewsRecommend.recommend(userIds);
//        contentBasedRecommend.recommend(userIds);
//        userPrefRefresherService.refresh();
//        userCFRecommendImpl.recommend(userIds);

        return new Result<>(1);
    }

    @GetMapping("/user_rec")
    public Result testRec(){

        List<Integer> userIds = this.iUserClientService.findAllUserId();
//        hotNewsRecommend.recommend(userIds);
        contentBasedRecommend.recommend(userIds);
//        userPrefRefresherService.refresh();
//        userCFRecommendImpl.recommend(userIds);

        return new Result<>(1);
    }

    @GetMapping("/qtz")
    public Result testQuartz() {
        Map<String, Long> map = new HashMap<>(2);
        map.put("id", 1L);
        // 先删除，再新增加
        quartzService.deleteJob("job", "test");
        quartzService.addJob(TestJob.class, "job", "test", "0/30 * * * * ?", map);
        return new Result<>(1);
    }
}
