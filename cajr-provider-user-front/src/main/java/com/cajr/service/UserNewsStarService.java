package com.cajr.service;

import com.cajr.vo.user.UserNewsStar;

/**
 * @author CAJR
 * @date 2020/5/8 1:49 下午
 */
public interface UserNewsStarService {

    int star(UserNewsStar userNewsStar);

    UserNewsStar getByUserIdAndNews(int userId, int newsId);

}
