package com.cajr.service;

import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.Map;

/**
 * @author CAJR
 * @date 2020/3/13 3:00 下午
 */
public interface QuartzService {
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, int jobTime,
                       int jobTimes, Map jobData);

    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, String jobTime, Map jobData);

    public void updateJob(String jobName, String jobGroupName, String jobTime);

    public void deleteJob(String jobName, String jobGroupName);

    public void pauseJob(String jobName, String jobGroupName);

    public void resumeJob(String jobName, String jobGroupName);

    public void runAJobNow(String jobName, String jobGroupName);

    public List<Map<String, Object>> queryAllJob();

    public List<Map<String, Object>> queryRunJob();
}
