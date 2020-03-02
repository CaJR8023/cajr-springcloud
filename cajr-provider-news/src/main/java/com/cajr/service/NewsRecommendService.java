package com.cajr.service;

import com.cajr.vo.news.NewsRecommend;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/2/26 6:09 下午
 */
public interface NewsRecommendService {

    List<NewsRecommend> findAllByUserId(int userId);

    List<NewsRecommend> findAllByUserIds(List<Integer> userIds);

    Integer insertRecommend(List<Integer> newsIds, int userId, int recAlgorithm);
}
