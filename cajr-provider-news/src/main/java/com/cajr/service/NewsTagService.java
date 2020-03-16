package com.cajr.service;

import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/16 2:02 下午
 */
public interface NewsTagService {

    Integer addOneModuleTag(ModuleTag moduleTag);

    Integer addOneNewsTag(NewsTag newsTag);

    Integer checkIsExistedByModuleIdAndTagId(int moduleId, int tagId);

    Integer checkIsExistedByNewsIdAndTagId(int newsId, int tagId);

    List<NewsTag> getAllTagByNewsId(int newsId);
}
