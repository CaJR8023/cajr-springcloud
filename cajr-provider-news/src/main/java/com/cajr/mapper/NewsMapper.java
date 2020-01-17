package com.cajr.mapper;

import com.cajr.vo.news.News;

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
}