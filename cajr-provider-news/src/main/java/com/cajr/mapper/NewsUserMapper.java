package com.cajr.mapper;

import com.cajr.vo.news.NewsUser;

/**
 * @author CAJR
 * @date 2020/5/8 2:15 下午
 */
public interface NewsUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsUser record);

    int insertSelective(NewsUser record);

    NewsUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsUser record);

    int updateByPrimaryKey(NewsUser record);
}