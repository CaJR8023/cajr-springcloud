package com.cajr.mapper;

import com.cajr.vo.tag.NewsTag;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/16 9:55 上午
 */
public interface TagNewsMapper {

    int countNewsNumByTagId(int tagId);

    List<Integer> getAllNewsIdByTagId(int tagId);
}