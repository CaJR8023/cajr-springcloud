package com.cajr.mapper;

import com.cajr.vo.user.UserLikeReply;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/21 4:35 下午
 */
public interface UserLikeReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLikeReply record);

    int insertSelective(UserLikeReply record);

    UserLikeReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLikeReply record);

    int updateByPrimaryKey(UserLikeReply record);

    List<UserLikeReply> selectAllByReplyId(Integer replyId);
}