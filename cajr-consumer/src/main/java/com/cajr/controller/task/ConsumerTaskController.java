package com.cajr.controller.task;

import com.cajr.service.ITaskClientService;
import com.cajr.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/4/13 7:15 下午
 */
@RestController
@RequestMapping("/qtz")
@Api(tags = "定时任务接口",value = "定时任务 rest接口")
public class ConsumerTaskController {
    @Autowired
    private ITaskClientService iTaskClientService;

    @GetMapping("/rec")
    public Result startRecommend(@RequestParam("isStart") int isStart) {
        return this.iTaskClientService.startRecommend(isStart);
    }

    @GetMapping("/news_data")
    public Result startCrawlNewsData(@RequestParam("isStart") int isStart) {
        return this.iTaskClientService.startCrawlNewsData(isStart);
    }
    @GetMapping("/runtime_job")
    public Result getRunTimeJob(){
        return this.iTaskClientService.getRunTimeJob();
    }
}
