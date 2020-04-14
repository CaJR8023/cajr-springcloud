package com.cajr.util;

/**
 * @author CAJR
 * @date 2020/3/2 2:57 下午
 */
public class CommonParam {
    //redis key name
    public static final String HOT_NEWS_REDIS_KEY = "hotNewsTop";
    public static final String NEWEST_NEWS_REDIS_KEY = "newestNewsTop";


    //Common Param other
    public static final int RETURN_SUCCESS = 1;
    public static final int RETURN_FAIL = 0;

    //Algorithm name
    //新闻热点推荐
    public static final int HR_ALGORITHM = 1;

    //协同过滤
    public static final int CF_ALGORITHM = 2;

    //基于内容推荐
    public static final int CB_ALGORITHM = 3;

    //最近的新闻的天数
    public static final int RECENT_NEWS_BEFORE_DAYS = 90;

    //定时任务相关
    public static final String REC_JOB_GROUP_NAME = "RECOMMEND-GROUP";
    public static final String REC_JOB_NAME = "RECOMMEND-JOB";
    public static final String NEWS_DATA_JOB_GROUP_NAME = "NEWS-DATA-GROUP";
    public static final String NEWS_DATA_JOB_NAME = "NEWS-DATA-JOB";

    //时间
    public static final int MON = 1;
    public static final int TUE = 2;
    public static final int WED = 3;
    public static final int THU = 4;
    public static final int FRI = 5;
    public static final int SAT = 6;
    public static final int SUN = 7;
}
