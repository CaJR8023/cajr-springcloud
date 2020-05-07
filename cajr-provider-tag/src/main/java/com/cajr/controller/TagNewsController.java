package com.cajr.controller;

import com.cajr.job.CountHottestTagJob;
import com.cajr.service.QuartzService;
import com.cajr.service.TagNewsService;
import com.cajr.util.CommonParam;
import com.cajr.util.Result;
import com.cajr.vo.tag.Tag;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CAJR
 * @date 2020/4/20 3:06 下午
 */
@RestController
@RequestMapping("/tag_news")
@Api(tags = "新闻标签接口",value = "提供增删改查 rest接口")
public class TagNewsController {

    @Autowired
    private TagNewsService tagNewsService;

    @Autowired
    private QuartzService quartzService;

    @GetMapping("/count_hot_tag")
    public Result countHottestTag(){
        this.tagNewsService.countHottestTag();
        return new Result<>(1);
    }

    @GetMapping("/tag_count")
    public Result countHottestTag(@RequestParam("isStart") int isStart){
        if (isStart == 1){
            Map<String, Long> map = new HashMap<>(2);
            map.put("id", 1L);
            // 先删除，再新增加
            quartzService.deleteJob(CommonParam.COUNT_HOTTEST_TAG_JOB_NAME, CommonParam.COUNT_HOTTEST_TAG_GROUP_NAME);
            quartzService.addJob(CountHottestTagJob.class, CommonParam.COUNT_HOTTEST_TAG_JOB_NAME, CommonParam.COUNT_HOTTEST_TAG_GROUP_NAME, "0/30 * * * * ?", map);
            return new Result<>(1);
        }else {
            quartzService.pauseJob(CommonParam.COUNT_HOTTEST_TAG_JOB_NAME, CommonParam.COUNT_HOTTEST_TAG_GROUP_NAME);
            return new Result<>(1);
        }
    }

    @GetMapping("/news")
    public List<Tag> getNewsTag(@RequestParam("newsId") Integer newsId){
        return this.tagNewsService.getNewsTag(newsId);
    }
}
