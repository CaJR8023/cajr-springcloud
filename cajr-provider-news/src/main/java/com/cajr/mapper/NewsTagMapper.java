package com.cajr.mapper;

import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/16 2:00 下午
 */
public interface NewsTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsTag record);

    int insertSelective(NewsTag record);

    NewsTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsTag record);

    int updateByPrimaryKey(NewsTag record);

    int checkIsExistedByNewsIdAndTagId(int newsId, int tagId);

    List<NewsTag> selectAllTagByNewsId(int newsId);

}
