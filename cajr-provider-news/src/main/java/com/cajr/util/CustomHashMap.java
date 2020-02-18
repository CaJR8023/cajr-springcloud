package com.cajr.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @Author CAJR
 * @create 2020/2/18 21:37
 */
public class CustomHashMap<K,V> extends HashMap<K,V> {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("{");
        for (K key : this.keySet()) {
            toString.append("\"").append(key).append("\":").append(this.get(key)).append(",");
        }
        if ("{".contentEquals(toString)){
            toString = new StringBuilder("{}");
        }else {
            toString = new StringBuilder(toString.toString().substring(0, toString.toString().length()-1)).append("}");
        }
        return toString.toString();
    }

    public CustomHashMap<K,V> copyFromLinkedHashMap(LinkedHashMap<K,V> linkedHashMap){
        this.putAll(linkedHashMap);
        return this;
    }
}
