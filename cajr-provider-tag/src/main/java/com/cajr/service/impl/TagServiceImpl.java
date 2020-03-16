package com.cajr.service.impl;

import com.cajr.mapper.TagMapper;
import com.cajr.service.TagService;
import com.cajr.vo.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author CAJR
 * @date 2020/3/16 10:15 上午
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Integer addOneTag(Tag tag) {
        tag.setStatus(1);
        tag.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        tag.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.tagMapper.insertSelective(tag);
        return tag.getId();
    }

    @Override
    public Tag getOneTagByName(String name) {
        return this.tagMapper.selectByPrimaryName(name);
    }

    @Override
    public Tag getOneTagById(int id) {
        return this.tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer checkTagIsExistedByName(String name) {
        return this.tagMapper.checkIsExistedByName(name);
    }
}
