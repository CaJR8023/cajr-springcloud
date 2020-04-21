package com.cajr.mapper;

import com.cajr.vo.news.Reply;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 1:20 下午
 */
public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    List<Reply> selectAllByReviewId(Integer reviewId);
}