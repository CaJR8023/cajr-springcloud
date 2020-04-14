package com.cajr.mapper;

import com.cajr.vo.user.Review;

/**
 * @author CAJR
 * @date 2020/4/6 7:37 下午
 */
public interface ReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
}