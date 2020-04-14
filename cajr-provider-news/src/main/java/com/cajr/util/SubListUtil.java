package com.cajr.util;

import java.util.List;

/**
 * list分页工具类
 * @author CAJR
 * @date 2020/4/6 11:18 下午
 */
public class SubListUtil {

    public static <T> void subList(List<T> list, List<T> list1, int page, int pageSize){
        int totalCount = list.size();
        int pageCount;
        if (totalCount%pageSize > 0){
            pageCount = totalCount/pageSize+1;
        }else {
            pageCount = totalCount/pageSize;
        }

        if (page == pageCount){
            list1.addAll(list.subList((page-1)*pageSize, totalCount));
            return;
        }

        for (int i = 1; i <= pageCount; i++) {
            if (i == page){
                list1.addAll(list.subList((i-1)*pageSize, pageSize*(i)));
            }
        }
    }
}
