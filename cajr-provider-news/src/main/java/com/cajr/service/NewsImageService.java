package com.cajr.service;

import com.cajr.vo.news.NewsImage;
import com.github.pagehelper.PageInfo;

/**
 * @author CAJR
 * @date 2020/1/17 3:54 下午
 */
public interface NewsImageService {
    Integer add(NewsImage newsImage);

    PageInfo getAllByPage(int page, int pageSize);
}
