package com.cajr.mapper;

import com.cajr.vo.news.Review;

import java.util.List;

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

    List<Review> selectAllByNewsId(Integer newsId);

    List<Review> selectAllByUserId(Integer userId);
}