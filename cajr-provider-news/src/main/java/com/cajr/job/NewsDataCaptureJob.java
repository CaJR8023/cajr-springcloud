package com.cajr.job;

import com.cajr.service.NewsDataService;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author CAJR
 * @date 2020/3/13 5:52 下午
 */
@Component
public class NewsDataCaptureJob extends QuartzJobBean {
    private static final Logger logger = Logger.getLogger(NewsDataCaptureJob.class);

    @Autowired
    NewsDataService newsDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            logger.info("新闻第三方数据定时获取  开始于" + new Date());
//            this.newsDataService.crawlChannelData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
