package com.cajr.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 定时把热点的新闻存进redis
 * @author CAJR
 * @date 2020/3/13 6:13 下午
 */
@Component
public class HotNewsMemoryJob extends QuartzJobBean {
    private static final Logger logger = Logger.getLogger(HotNewsMemoryJob.class);

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
