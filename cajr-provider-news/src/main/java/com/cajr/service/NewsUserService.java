package com.cajr.service;

import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsUser;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/5/8 4:01 下午
 */
public interface NewsUserService {

    List<News> getByUserId(int userId);

    int collect(NewsUser newsUser);
}
