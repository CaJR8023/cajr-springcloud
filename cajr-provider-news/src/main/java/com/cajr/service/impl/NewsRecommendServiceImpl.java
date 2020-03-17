package com.cajr.service.impl;

import com.cajr.mapper.NewsRecommendMapper;
import com.cajr.service.NewsRecommendService;
import com.cajr.util.CommonParam;
import com.cajr.vo.news.NewsRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author CAJR
 * @date 2020/2/26 6:12 下午
 */
@Service
public class NewsRecommendServiceImpl implements NewsRecommendService {

    @Autowired
    private NewsRecommendMapper newsRecommendMapper;

    @Override
    public List<NewsRecommend> findAllByUserId(int userId) {
        return this.newsRecommendMapper.findAllByUserId(userId);
    }

    @Override
    public List<NewsRecommend> findAllByUserIds(List<Integer> userIds) {
        if (userIds.isEmpty()){
            return new ArrayList<>();
        }
        return this.newsRecommendMapper.findAllByUserIds(userIds);
    }

    @Override
    public Integer insertRecommend(List<Integer> newsIds, int userId, int recAlgorithm, Map<Integer, Double> tempMatchMap) {
        if (!newsIds.isEmpty()){
            for (Integer newsId : newsIds) {
                NewsRecommend newsRecommend = new NewsRecommend();
                newsRecommend.setNewsId(newsId);
                newsRecommend.setUserId(userId);
                newsRecommend.setFeedback(recAlgorithm);

                if (recAlgorithm == CommonParam.HR_ALGORITHM){
                    newsRecommend.setSuitability(0.0);
                }else {
                    newsRecommend.setSuitability(tempMatchMap.get(newsId));
                }

                newsRecommend.setStatus(1);
                newsRecommend.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
                newsRecommend.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

                if (this.newsRecommendMapper.checkRecommendByUserIdAndNewsId(userId, newsId) > 0){
                    return CommonParam.RETURN_FAIL;
                }
                this.newsRecommendMapper.insertSelective(newsRecommend);
            }
            return CommonParam.RETURN_SUCCESS;
        }
        return CommonParam.RETURN_FAIL;
    }
}
