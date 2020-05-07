package com.cajr.service.impl;

import com.cajr.lock.DistributedLock;
import com.cajr.lock.impl.RedisDistributedLock;
import com.cajr.mapper.NewsRecommendMapper;
import com.cajr.service.NewsLogsService;
import com.cajr.service.NewsRecommendService;
import com.cajr.service.NewsService;
import com.cajr.service.RecommendService;
import com.cajr.util.CommonParam;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.CountNewsRecommendResult;
import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsRecommend;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsLogsService newsLogsService;

    @Autowired
    private RecommendService hotNewsRecommend;

    @Autowired
    private RecommendService contentBasedRecommend;

    @Autowired
    private RecommendService userCFRecommendImpl;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String LOCK_KEY = "news_rec_lock";

    public static final String REC_REQUEST_NUM_KEY = "news-rec-request-";

    @Override
    public List<NewsRecommend> findAllByUserId(int userId) {
        return this.newsRecommendMapper.findAllByUserId(userId);
    }

    @Override
    public PageInfo recNewsList(int userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo<>(this.newsRecommendMapper.findAllByUserId(userId));
        List<NewsRecommend> recommends = pageInfo.getList();
        if (CollectionUtils.isEmpty(recommends)){
            return pageInfo;
        }
        recommends.forEach(recommend -> {
            News news = this.newsService.getOne(recommend.getNewsId());
            news.setBrowseStatus(this.newsLogsService.checkExistByUserIdAndNewsId(userId, news.getId()));
            recommend.setNews(news);
        });
        pageInfo.setList(recommends);
        return pageInfo;
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

    @Override
    public Integer checkNumByUserId(Integer userId) {
        return this.newsRecommendMapper.findAllByUserId(userId).size();
    }

    @Override
    public void recommend(Integer userId) {
        String lockLogo = null;
        //加锁
        DistributedLock distributedLock = new RedisDistributedLock(redisTemplate,LOCK_KEY,60);
        try {
            do {
                lockLogo = distributedLock.acquireLock();
                this.redisTemplate.opsForValue().set(REC_REQUEST_NUM_KEY+userId, "false");
            } while (lockLogo == null);
            List<Integer> userIds = new ArrayList<>();
            userIds.add(userId);
            this.contentBasedRecommend.recommend(userIds);
            this.userCFRecommendImpl.recommend(userIds);
            this.hotNewsRecommend.recommend(userIds);
        }finally {
            this.redisTemplate.opsForValue().set(REC_REQUEST_NUM_KEY+userId, "true");
            while(true){
                boolean unLock = distributedLock.releaseLock(lockLogo);
                if (unLock){
                    break;
                }
            }
        }
    }
}
