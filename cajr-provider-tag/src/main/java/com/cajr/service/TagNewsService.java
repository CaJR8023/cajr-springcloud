package com.cajr.service;

import com.cajr.vo.tag.Tag;
import com.cajr.vo.tag.TagOther;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 3:06 下午
 */
public interface TagNewsService {

    TagOther getNewsList(int tagId);

    void countHottestTag();

    List<Tag> getNewsTag(Integer newsId);
}
