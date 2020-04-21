package com.cajr.service.impl;

import com.cajr.mapper.TagMapper;
import com.cajr.service.INewsClientService;
import com.cajr.service.TagService;
import com.cajr.util.CommonParam;
import com.cajr.vo.news.News;
import com.cajr.vo.tag.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author CAJR
 * @date 2020/3/16 10:15 上午
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private INewsClientService iNewsClientService;

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
        Tag tag = this.tagMapper.selectByPrimaryKey(id);
        if (tag != null){
            List<Integer> newsIds = tag.getNewsIds();
            if (!newsIds.isEmpty()){
                List<News> newsList = new ArrayList<>();
                for (Integer newsId : newsIds) {
                    newsList.add(this.iNewsClientService.getOne(newsId));
                }
                tag.setNewsList(newsList);
            }
        }
        return tag;
    }

    @Override
    public Integer checkTagIsExistedByName(String name) {
        return this.tagMapper.checkIsExistedByName(name);
    }

    @Override
    public PageInfo getAllByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(this.tagMapper.findAll());
    }

    @Override
    public List<Tag> getAll() {
        return this.tagMapper.selectAll();
    }

    @Override
    public List<Tag> getHottestTags() {
        Set<ZSetOperations.TypedTuple<Object>> hotTagsRedisTopIds = redisTemplate.opsForZSet().reverseRangeWithScores(CommonParam.HOT_TAG_REDIS_KEY,0,-1);
        List<Integer> hottestTagIds = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        assert hotTagsRedisTopIds != null;
        if (hotTagsRedisTopIds.isEmpty()){
            return tags;
        }
        int index = 0;
        for (ZSetOperations.TypedTuple<Object> z : hotTagsRedisTopIds) {
            if (index < 5){
                hottestTagIds.add(Integer.parseInt((String) Objects.requireNonNull(z.getValue())));
                index ++;
            }else {
                break;
            }
        }
        tags = this.tagMapper.selectAllByIds(hottestTagIds);
        return tags;
    }

    @Override
    public List<Tag> getAllTagByIds(List<Integer> tagIds) {
        return this.tagMapper.selectAllByIds(tagIds);
    }
}
