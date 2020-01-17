package com.cajr.mapper;

import com.cajr.vo.news.NewsImage;

/**
 * @author CAJR
 * @date 2020/1/17 3:51 下午
 */
public interface NewsImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsImage record);

    int insertSelective(NewsImage record);

    NewsImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsImage record);

    int updateByPrimaryKey(NewsImage record);
}