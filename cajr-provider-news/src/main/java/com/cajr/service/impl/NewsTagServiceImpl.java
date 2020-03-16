package com.cajr.service.impl;

import com.cajr.mapper.ModuleTagMapper;
import com.cajr.mapper.NewsTagMapper;
import com.cajr.service.NewsTagService;
import com.cajr.util.CommonParam;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/16 2:03 下午
 */
@Service
public class NewsTagServiceImpl implements NewsTagService {


    @Autowired
    private ModuleTagMapper moduleTagMapper;

    @Autowired
    private NewsTagMapper newsTagMapper;

    @Override
    public Integer addOneModuleTag(ModuleTag moduleTag) {
        if (checkIsExistedByModuleIdAndTagId(moduleTag.getModuleId(), moduleTag.getTagId()) > 0 ){
            return CommonParam.RETURN_FAIL;
        }

        moduleTag.setStatus(1);
        moduleTag.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        moduleTag.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.moduleTagMapper.insertSelective(moduleTag);
    }

    @Override
    public Integer addOneNewsTag(NewsTag newsTag) {
        if (checkIsExistedByNewsIdAndTagId(newsTag.getNewsId(), newsTag.getTagId()) > 0){
            return CommonParam.RETURN_FAIL;
        }

        newsTag.setStatus(1);
        newsTag.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        newsTag.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.newsTagMapper.insertSelective(newsTag);
    }


    @Override
    public Integer checkIsExistedByModuleIdAndTagId(int moduleId, int tagId) {
        return this.moduleTagMapper.checkIsExistedByModuleIdAndTagId(moduleId, tagId);
    }

    @Override
    public Integer checkIsExistedByNewsIdAndTagId(int newsId, int tagId) {
        return this.newsTagMapper.checkIsExistedByNewsIdAndTagId(newsId, tagId);
    }

    @Override
    public List<NewsTag> getAllTagByNewsId(int newsId) {
        return null;
    }
}
