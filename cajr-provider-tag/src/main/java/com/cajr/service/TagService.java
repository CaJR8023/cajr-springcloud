package com.cajr.service;

import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;

/**
 * @author CAJR
 * @date 2020/3/16 10:15 上午
 */
public interface TagService {

    Integer addOneTag(Tag tag);

    Integer addOneModuleTag(ModuleTag moduleTag);

    Integer addOneNewsTag(NewsTag newsTag);

    Tag getOneTagByName(String name);

    Integer checkTagIsExistedByName(String name);

    Integer checkIsExistedByModuleIdAndTagId(int moduleId, int tagId);

    Integer checkIsExistedByNewsIdAndTagId(int newsId, int tagId);
}
