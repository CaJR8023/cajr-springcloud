package com.cajr.service;

import com.cajr.util.CustomHashMap;

import java.util.HashMap;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/2 5:39 下午
 */
public interface RecommendCommonService {

    /**
     * 过滤掉已经被该用户看过的新闻id
     * @param newsIds 新闻列表
     * @param userId 用户id
     */
    public void filterBrowsedNews(List<Integer> newsIds, Integer userId);

    /**
     * 过滤已经推荐给该用户的新闻id
     * @param newsIds 新闻列表
     * @param userId 用户id
     */
    public void filterRecCedNews(List<Integer> newsIds, Integer userId);

    /**
     * 过滤掉失去时效的新闻
     * @param newsIds 新闻列表
     * @param userId 用户id
     */
    public void filterOutDateNews(List<Integer> newsIds, Integer userId);

    /**
     * 获取用户的喜好关键词
     * @param userIds
     * @return
     */
    public HashMap<Integer, CustomHashMap<Integer, CustomHashMap<String,Double>>> getUserPrefList(List<Integer> userIds);

}
