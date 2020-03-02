package com.cajr.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/2 3:36 下午
 */
public class RecommendKit {

    /**
     * 去除数量上超过为算法设置的推荐结果上限值的推荐结果
     * @param newsIds 新闻id列表
     * @param n 上限数量
     */
    public static void removeOverNews(List<Integer> newsIds, int n){
        int i = 0;
        Iterator<Integer> iterator = newsIds.iterator();
        while (iterator.hasNext()){
            if (i >= n){
                iterator.remove();
                iterator.next();
            }else {
                iterator.next();
            }
            i++;
        }
    }
}
