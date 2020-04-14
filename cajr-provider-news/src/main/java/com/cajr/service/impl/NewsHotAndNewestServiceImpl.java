package com.cajr.service.impl;

import com.cajr.service.NewsHotAndNewestService;
import com.cajr.service.NewsLogsService;
import com.cajr.service.NewsService;
import com.cajr.util.SubListUtil;
import com.cajr.vo.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author CAJR
 * @date 2020/4/6 9:30 下午
 */
@Service
public class NewsHotAndNewestServiceImpl implements NewsHotAndNewestService {

    @Autowired
    NewsService newsService;

    @Autowired
    NewsLogsService newsLogsService;

    @Override
    public void hotNewsExtract() {

    }

    @Override
    public List<News> newestNewsExtract(int page, int pageSize) {
        List<News> newsList = this.newsService.findAll();
        List<News> resultList = new ArrayList<>();
        sortByTime(newsList);
        SubListUtil.subList(newsList, resultList, page, pageSize);
        return resultList;
    }

    private void sortByTime(List<News> newsList){
        newsList.sort((o1, o2) -> {
            if (o1.getCreatedAt().before(o2.getCreatedAt())) {
                return 1;
            } else {
                return 1;
            }
        });
    }
}
