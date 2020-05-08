package com.cajr.mapper;

import com.cajr.vo.user.UserNewsStar;

/**
 * @author CAJR
 * @date 2020/5/8 1:48 下午
 */
public interface UserNewsStarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserNewsStar record);

    int insertSelective(UserNewsStar record);

    UserNewsStar selectByPrimaryKey(Integer id);

    UserNewsStar selectByUserIdAndNewsId(int userId, int newsId);

    int updateByPrimaryKeySelective(UserNewsStar record);

    int updateByPrimaryKey(UserNewsStar record);

    int checkExisted(UserNewsStar userNewsStar);
}