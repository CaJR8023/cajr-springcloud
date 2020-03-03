package com.cajr.util;

/**
 * @author CAJR
 * @date 2020/3/2 2:57 下午
 */
public class CommonParam {
    //redis key name
    public static final String HOT_NEWS_REDIS_KEY = "hotNewsTop";


    //Common Param other
    public static final int RETURN_SUCCESS = 1;
    public static final int RETURN_FAIL = 0;

    //Algorithm name
    //协同过滤
    public static final int HR_ALGORITHM = 1;

    //基于内容推荐
    public static final int CF_ALGORITHM = 2;

    //新闻热点推荐
    public static final int CB_ALGORITHM = 3;
}
