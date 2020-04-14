package com.cajr.controller;

import com.cajr.job.NewsDataCaptureJob;
import com.cajr.job.TestJob;
import com.cajr.service.QuartzService;
import com.cajr.util.CommonParam;
import com.cajr.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CAJR
 * @date 2020/4/13 6:19 下午
 */
@RequestMapping("/qtz")
@Api(tags = "定时任务接口",value = "定时任务 rest接口")
@RestController
public class TaskController {

    @Autowired
    private QuartzService quartzService;

    @GetMapping("/rec")
    public Result startRecommend(@RequestParam("isStart") int isStart) {
        if (isStart == 1){
            Map<String, Long> map = new HashMap<>(2);
            map.put("id", 1L);
            // 先删除，再新增加
            quartzService.deleteJob(CommonParam.REC_JOB_NAME, CommonParam.REC_JOB_GROUP_NAME);
            quartzService.addJob(TestJob.class, CommonParam.REC_JOB_NAME, CommonParam.REC_JOB_GROUP_NAME, "0/30 * * * * ?", map);
            return new Result<>(1);
        }else {
            quartzService.pauseJob(CommonParam.REC_JOB_NAME, CommonParam.REC_JOB_GROUP_NAME);
            return new Result<>(1);
        }
    }

    @GetMapping("/news_data")
    public Result startCrawlNewsData(@RequestParam("isStart") int isStart) {
        if (isStart == 1){
            Map<String, Long> map = new HashMap<>(2);
            map.put("id", 1L);
            // 先删除，再新增加
            quartzService.deleteJob(CommonParam.NEWS_DATA_JOB_NAME, CommonParam.NEWS_DATA_JOB_GROUP_NAME);
            quartzService.addJob(NewsDataCaptureJob.class, CommonParam.NEWS_DATA_JOB_NAME, CommonParam.NEWS_DATA_JOB_GROUP_NAME, "0/30 * * * * ?", map);
            return new Result<>(1);
        }else {
            quartzService.pauseJob(CommonParam.NEWS_DATA_JOB_NAME, CommonParam.NEWS_DATA_JOB_GROUP_NAME);
            return new Result<>(1);
        }
    }

    @GetMapping("/runtime_job")
    public Result getRunTimeJob(){
        return new Result<>(this.quartzService.queryRunJob());
    }
}
