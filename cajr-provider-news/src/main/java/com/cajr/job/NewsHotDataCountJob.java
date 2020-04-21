package com.cajr.job;

import com.cajr.service.NewsHotAndNewestService;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author CAJR
 * @date 2020/4/20 7:37 下午
 */
@Component
public class NewsHotDataCountJob extends QuartzJobBean {
    private static final Logger logger = Logger.getLogger(NewsHotDataCountJob.class);
    @Autowired
    private NewsHotAndNewestService newsHotAndNewestService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("开启热点新闻统计并记录，开始于" + Timestamp.valueOf(LocalDateTime.now()));
        this.newsHotAndNewestService.newestNews();
    }
}
