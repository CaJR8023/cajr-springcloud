package com.cajr.mapper;

import com.cajr.vo.news.NewsRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/2/26 6:03 下午
 */
public interface NewsRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsRecommend record);

    int insertSelective(NewsRecommend record);

    NewsRecommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsRecommend record);

    int updateByPrimaryKey(NewsRecommend record);

    List<NewsRecommend> findAllByUserId(int userId);

    List<NewsRecommend> findAllByUserIds(@Param("userIds")List<Integer> userIds);
}