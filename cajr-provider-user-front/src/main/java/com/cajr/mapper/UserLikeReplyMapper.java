package com.cajr.mapper;

import com.cajr.vo.user.UserLikeReply;

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
}