package com.cajr.job;

import com.cajr.service.RecommendService;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author CAJR
 * @date 2020/3/13 5:58 下午
 */
@Component
public class CBRecommendJob extends QuartzJobBean {
    private static final Logger logger = Logger.getLogger(CBRecommendJob.class);


    @Autowired
    private RecommendService contentBasedRecommend;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("开启基于内容推荐");
    }
}
