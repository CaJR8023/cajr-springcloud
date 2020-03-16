package com.cajr.mapper;

import com.cajr.vo.tag.NewsTag;

/**
 * @author CAJR
 * @date 2020/3/16 9:55 上午
 */
public interface NewsTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsTag record);

    int insertSelective(NewsTag record);

    NewsTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsTag record);

    int updateByPrimaryKey(NewsTag record);

    int checkIsExistedByNewsIdAndTagId(int newsId, int tagId);
}