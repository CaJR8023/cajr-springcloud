package com.cajr.service;

import java.util.List;

/**
 * @Author CAJR
 * @create 2020/2/20 13:47
 */
public interface NewsRecommendService {

    /**
     * 针对特定用户推荐新闻
     * @param userIds 用户id列表
     */
    public void recommend(List<Integer> userIds);
}
