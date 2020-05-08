package com.cajr.service;

import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsLogs;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/3 10:07 上午
 */
public interface NewsLogsService {

    List<News> findAllByUserId(Integer userId);

    List<NewsLogs> findAll();

    Integer add(NewsLogs newsLogs);

    Integer update(NewsLogs newsLogs);

    List<News> getAllNewsByUserId(Integer userId);

    Integer checkExistByUserIdAndNewsId(Integer userId, Integer newsId);

    NewsLogs getByUserIdAndNewsId(Integer userId, Integer newsId);

    Integer delete(Integer id);

    PageInfo getAllByPage(int page, int pageSize);

}
