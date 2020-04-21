package com.cajr.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/3/13 6:00 下午
 */
@Component
public class CFRecommendJob extends QuartzJobBean {
    private static final Logger logger = Logger.getLogger(CFRecommendJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("开启协同过滤推荐");
    }
}
