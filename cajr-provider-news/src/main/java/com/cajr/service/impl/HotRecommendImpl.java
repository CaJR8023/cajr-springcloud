package com.cajr.service.impl;

import com.cajr.service.NewsRecommendService;
import com.cajr.service.RecommendCommonService;
import com.cajr.service.RecommendService;
import com.cajr.util.CommonParam;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.NewsRecommend;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author CAJR
 * @create 2020/2/20 14:10
 */
@Service("hotNewsRecommend")
public class HotRecommendImpl implements RecommendService {

    private static final Logger logger = Logger.getLogger(HotRecommendImpl.class);

    public static int beforeDays = -3;

    public static int TOTAL_REC_NUM = 50;

    @Autowired
    private NewsRecommendService newsRecommendService;

    @Autowired
    private RecommendCommonService recommendCommonService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void recommend(List<Integer> userIds) {
        logger.info("热点补充算法开始 " + Timestamp.valueOf(LocalDateTime.now()));

        //将每天生成的“热点新闻”ID，按照新闻的热点程度从高到低放入redis,取出来
        Set<ZSetOperations.TypedTuple<Object>> hotNewsRedisTopIds = redisTemplate.opsForZSet().reverseRangeWithScores(CommonParam.HOT_NEWS_REDIS_KEY,0,-1);
        if (hotNewsRedisTopIds == null || hotNewsRedisTopIds.size() == 0){
            return;
        }

        //转换成List
        List<Integer> hotNewsTopIds = new ArrayList<>();
        for (ZSetOperations.TypedTuple<Object> hotNewsTopId : hotNewsRedisTopIds) {
            hotNewsTopIds.add(Integer.parseInt((String) Objects.requireNonNull(hotNewsTopId.getValue())));
        }

        if (userIds.isEmpty()){
            return;
        }
        int count = 0;

        //时间
        Calendar calendar = Calendar.getInstance();
        Date date = TimeUtil.getCertainTimestamp(calendar.get(Calendar.YEAR), calendar.get(Calendar.DAY_OF_MONTH)+1, beforeDays, 0, 0, 0);
        Map<Integer, Integer> userIdToNewsRecommendNumMap = new HashMap<>(16);
        //数据库取出用户的推荐新闻，并统计各个用户的推荐新闻的数量
        List<NewsRecommend> newsRecommends = this.newsRecommendService.findAllByUserIds(userIds);
        if (!newsRecommends.isEmpty()){
            for (Integer userId: userIds){
                int num = 0;
                for (NewsRecommend newsRecommend : newsRecommends) {
                    if (newsRecommend.getUserId() == userId){
                        num++;
                    }
                }
                userIdToNewsRecommendNumMap.put(userId,num);
            }
        }

        for (Integer userId : userIds) {

            int tmpRecNums = userIdToNewsRecommendNumMap.get(userId);
            boolean flag = (tmpRecNums!=0);
            int delta = flag?TOTAL_REC_NUM - tmpRecNums : TOTAL_REC_NUM;

            List<Integer> toBeRecommended = new ArrayList<>();
            Map<Integer, Double> tempMatchMap = new HashMap<>();
            if(delta > 0){
                int index = Math.min(hotNewsTopIds.size(), delta);
                while (index-- > 0){
                    toBeRecommended.add(hotNewsTopIds.get(index));
                }
            }
            this.recommendCommonService.filterBrowsedNews(toBeRecommended, userId);
            this.recommendCommonService.filterRecCedNews(toBeRecommended, userId);
            this.newsRecommendService.insertRecommend(toBeRecommended, userId, CommonParam.HR_ALGORITHM, tempMatchMap);
            count += toBeRecommended.size();

        }

        logger.info("HR has contributed " + (userIds.size()==0?0:count/userIds.size()) + " recommending news on average");
        logger.info("HR end at "+ Timestamp.valueOf(LocalDateTime.now()));
    }

}
