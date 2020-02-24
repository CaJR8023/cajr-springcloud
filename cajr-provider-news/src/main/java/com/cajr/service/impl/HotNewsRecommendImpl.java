package com.cajr.service.impl;

import com.cajr.service.NewsRecommendService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author CAJR
 * @create 2020/2/20 14:10
 */
@Service("HotNewsRecommend")
public class HotNewsRecommendImpl implements NewsRecommendService {

    public static final Logger logger = Logger.getLogger(HotNewsRecommendImpl.class);

    public static int beforeDays = -3;

    public static int TOTAL_REC_NUM = 50;

    @Override
    public void recommend(List<Integer> userIds) {

    }
}
