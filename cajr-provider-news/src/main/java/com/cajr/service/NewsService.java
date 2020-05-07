package com.cajr.service;

import com.cajr.vo.SearchPage;
import com.cajr.vo.news.News;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/1/15 4:38 下午
 */
public interface NewsService {

    Integer add(News news);

    List<News> findSectionNews(List<Integer> newsIds);

    List<News> findAll();

    List<News> findAllByModuleId(int moduleId);

    PageInfo<News> findAllByPage(int pageNum, int pageSize);

    List<Integer> findAllNewsId();

    Integer update(News news);

    Integer deleteOne(Integer id);

    News getOne(int id);

    SearchPage searchNews(String keyWord, int pageNum, int pageSize);

}
