package com.cajr.job;

import com.cajr.service.ITagClientService;
import com.cajr.service.TagNewsService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

/**
 * @author CAJR
 * @date 2020/4/20 4:37 下午
 */
@Component
public class CountHottestTagJob extends QuartzJobBean {
    private static final Logger logger = Logger.getLogger(CountHottestTagJob.class);

    @Autowired
    private TagNewsService tagNewsService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("统计热门标签任务启动");
        this.tagNewsService.countHottestTag();
    }
}
