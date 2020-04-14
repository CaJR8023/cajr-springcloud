package com.cajr.service;

import com.cajr.vo.news.News;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/6 9:30 下午
 */
public interface NewsHotAndNewestService {
    void hotNewsExtract();

    List<News> newestNewsExtract(int page, int pageSize);
}
