package com.cajr.mapper;

import com.cajr.vo.news.Review;

/**
 * @author CAJR
 * @date 2020/4/20 1:18 下午
 */
public interface ReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);

    int findNumByNewsId(Integer newsId);
}