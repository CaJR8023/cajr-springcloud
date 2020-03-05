package com.cajr.service.impl;

import com.cajr.service.RecommendService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/3 5:19 下午
 */
@Service("ContentBasedRecommendImpl")
public class ContentBasedRecommendImpl implements RecommendService {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(ContentBasedRecommendImpl.class);



    @Override
    public void recommend(List<Integer> userIds) {
        int count = 0;
        logger.info("基于内容算法开始推荐" + Timestamp.valueOf(LocalDateTime.now()));

    }
}
