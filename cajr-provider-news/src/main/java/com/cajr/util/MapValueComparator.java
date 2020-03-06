package com.cajr.util;

import java.util.Comparator;
import java.util.Map;

/**
 * @Author CAJR
 * @create 2020/2/18 22:36
 */
public class MapValueComparator implements Comparator<Map.Entry<Integer,Double>> {
    @Override
    public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
        return o1.getValue().compareTo(o2.getValue());
    }
}
