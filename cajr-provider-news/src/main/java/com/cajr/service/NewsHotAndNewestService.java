package com.cajr.service;

import com.cajr.vo.news.News;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/6 9:30 下午
 */
public interface NewsHotAndNewestService {
    void hotNewsExtract();

    void newestNews();

    PageInfo newestNewsExtract(int page, int pageSize);

    List<News> get24HoursHotNews();
}
