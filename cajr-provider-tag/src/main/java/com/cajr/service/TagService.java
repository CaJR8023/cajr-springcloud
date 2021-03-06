package com.cajr.service;

import com.cajr.vo.tag.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/16 10:15 上午
 */
public interface TagService {

    Integer addOneTag(Tag tag);

    Tag getOneTagByName(String name);

    Tag getOneTagById(int id);

    Integer checkTagIsExistedByName(String name);

    PageInfo getAllByPage(int page, int pageSize);

    List<Tag> getAll();

    List<Tag> getHottestTags();

    List<Tag> getAllTagByIds(List<Integer> tagIds);
}
