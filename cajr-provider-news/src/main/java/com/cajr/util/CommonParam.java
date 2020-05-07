package com.cajr.util;

import com.aliyun.opensearch.sdk.dependencies.com.google.common.collect.Lists;

import java.util.ArrayList;

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
    public static final String COUNT_NEWEST_NEWS_GROUP_NAME = "COUNT_HOTTEST_TAG-GROUP";
    public static final String COUNT_NEWEST_NEWS_JOB_NAME = "COUNT_HOTTEST_TAG-JOB";

    //时间
    public static final int MON = 1;
    public static final int TUE = 2;
    public static final int WED = 3;
    public static final int THU = 4;
    public static final int FRI = 5;
    public static final int SAT = 6;
    public static final int SUN = 7;

    //判断是否图片
    static final String IS_IMG_STRING = "url";
    static final String IS_IMG_STRING1 = "width";
    static final String IMG_HTML_FRONT = "<img src=\"";
    static final String IMG_HTML_AFTER = "\" width=\"100%\" height=\"100%\"/>";

    //news Search
    public static final String NEWS_TABLE_NAME = "news";
    public static final ArrayList<String> openSearchFetchFieldFormat = Lists.newArrayList("news_id","title","content","created_at");
    public static final String DISTINCT_STATEMENT = "&&distinct=dist_key:order_group_id,dist_count:1,dist_times:1,reserved:false && sort= -created_at";


}
