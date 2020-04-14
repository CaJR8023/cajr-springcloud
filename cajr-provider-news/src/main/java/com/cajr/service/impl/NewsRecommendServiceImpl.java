package com.cajr.service.impl;

import com.cajr.mapper.NewsRecommendMapper;
import com.cajr.service.NewsRecommendService;
import com.cajr.util.CommonParam;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.CountNewsRecommendResult;
import com.cajr.vo.news.NewsRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    public List<NewsRecommend> findAllByFeedback(int feedback) {

        Timestamp timestamp = TimeUtil.getMondayOfThisWeek();

        return this.newsRecommendMapper.findAllByFeedback(feedback);
    }

    @Override
    public CountNewsRecommendResult countRecNewsNum() {
        CountNewsRecommendResult countNewsRecommendResult = new CountNewsRecommendResult();
        Timestamp timestamp = TimeUtil.getMondayOfThisWeek();
        //挑选出本周一之后的推荐记录
        List<NewsRecommend> newsRecommends = this.newsRecommendMapper.findAll();
        List<NewsRecommend> newNewsRecommends = new ArrayList<>();
        newsRecommends.forEach(newsRecommend -> {
            if (newsRecommend.getCreatedAt().after(timestamp)){
                newNewsRecommends.add(newsRecommend);
            }
        });
        if (!newNewsRecommends.isEmpty()){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(timestamp);
            List<Integer> cbNums = new ArrayList<>();
            List<Integer> cfNums = new ArrayList<>();
            List<Integer> hrNums = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                calendar.add(Calendar.DAY_OF_WEEK, i);
                AtomicInteger cbNum = new AtomicInteger();
                AtomicInteger cfNum = new AtomicInteger();
                AtomicInteger hrNum = new AtomicInteger();
                newNewsRecommends.forEach(newsRecommend -> {
                    if (newsRecommend.getCreatedAt().after(calendar.getTime())){
                        if (newsRecommend.getFeedback() == CommonParam.CB_ALGORITHM){
                            cbNum.getAndIncrement();
                        }else if (newsRecommend.getFeedback() == CommonParam.CF_ALGORITHM){
                            cfNum.getAndIncrement();
                        } else if (newsRecommend.getFeedback() == CommonParam.HR_ALGORITHM) {
                            hrNum.getAndIncrement();
                        }
                    }
                });

                cbNums.add(cbNum.get());
                cfNums.add(cfNum.get());
                hrNums.add(hrNum.get());
            }
            countNewsRecommendResult = new CountNewsRecommendResult(cbNums, cfNums, hrNums);
        }
        return countNewsRecommendResult;
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
