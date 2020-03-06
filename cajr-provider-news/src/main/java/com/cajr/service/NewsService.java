package com.cajr.service;

import com.cajr.vo.news.News;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/1/15 4:38 下午
 */
public interface NewsService {
    Integer add(News news);

    List<News> findSectionNews(List<Integer> newsIds);

    List<News> findAll();
}
