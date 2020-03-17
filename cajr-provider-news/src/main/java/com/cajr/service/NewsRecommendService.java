package com.cajr.service;

import com.cajr.vo.news.NewsRecommend;

import java.util.List;
import java.util.Map;

/**
 * @author CAJR
 * @date 2020/2/26 6:09 下午
 */
public interface NewsRecommendService {

    List<NewsRecommend> findAllByUserId(int userId);

    List<NewsRecommend> findAllByUserIds(List<Integer> userIds);

    Integer insertRecommend(List<Integer> newsId, int userId, int recAlgorithm, Map<Integer, Double> tempMatchMap);
}
