package com.cajr.mapper;

import com.cajr.vo.news.News;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/1/17 3:51 下午
 */
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    int checkExistBySign(String sign);

    List<News> selectSectionByNewsIds(List<Integer> newsId);

    List<News> selectAll();

    List<News> selectAllSortByTime();

    List<News> selectAllByModuleId(Integer moduleId);

    List<Integer> selectAllId();
}