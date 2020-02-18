package com.cajr.util;

import java.util.Comparator;
import java.util.Map;

/**
 * @Author CAJR
 * @create 2020/2/18 21:53
 */
public class CustomComparator implements Comparator<String> {
    private Map<String,Double> baseMap;

    public CustomComparator(Map<String, Double> baseMap) {
        this.baseMap = baseMap;
    }

    @Override
    public int compare(String o1, String o2) {
        if (baseMap.get(o1) >= baseMap.get(o2)){
            return 1;
        }
        return 0;
    }
}
