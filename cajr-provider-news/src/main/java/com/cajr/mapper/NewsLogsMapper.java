package com.cajr.mapper;

import com.cajr.vo.news.NewsLogs;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/3 10:04 上午
 */
public interface NewsLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsLogs record);

    int insertSelective(NewsLogs record);

    NewsLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsLogs record);

    int updateByPrimaryKey(NewsLogs record);

    List<NewsLogs> findAllByUserId(int userId);

    List<NewsLogs> findAll();

    int checkExistByUserIdAndNewsId(Integer userId, Integer newsId);

    int findNumByNewsId(Integer newsId);
}