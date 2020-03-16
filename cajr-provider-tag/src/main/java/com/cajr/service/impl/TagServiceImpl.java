package com.cajr.service.impl;

import com.cajr.mapper.ModuleTagMapper;
import com.cajr.mapper.NewsTagMapper;
import com.cajr.mapper.TagMapper;
import com.cajr.service.TagService;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CAJR
 * @date 2020/3/16 10:15 上午
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ModuleTagMapper moduleTagMapper;

    @Autowired
    private NewsTagMapper newsTagMapper;


    @Override
    public Integer addOneTag(Tag tag) {
        this.tagMapper.insertSelective(tag);
        return tag.getId();
    }

    @Override
    public Integer addOneModuleTag(ModuleTag moduleTag) {
        return this.moduleTagMapper.insertSelective(moduleTag);
    }

    @Override
    public Integer addOneNewsTag(NewsTag newsTag) {
        return this.newsTagMapper.insertSelective(newsTag);
    }

    @Override
    public Tag getOneTagByName(String name) {
        return this.tagMapper.selectByPrimaryName(name);
    }

    @Override
    public Integer checkTagIsExistedByName(String name) {
        return this.tagMapper.checkIsExistedByName(name);
    }

    @Override
    public Integer checkIsExistedByModuleIdAndTagId(int moduleId, int tagId) {
        return this.moduleTagMapper.checkIsExistedByModuleIdAndTagId(moduleId, tagId);
    }

    @Override
    public Integer checkIsExistedByNewsIdAndTagId(int newsId, int tagId) {
        return this.newsTagMapper.checkIsExistedByNewsIdAndTagId(newsId, tagId);
    }
}
