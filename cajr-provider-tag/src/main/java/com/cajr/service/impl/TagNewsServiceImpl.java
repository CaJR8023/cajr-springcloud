package com.cajr.service.impl;

import com.cajr.mapper.TagNewsMapper;
import com.cajr.service.TagNewsService;
import com.cajr.service.TagService;
import com.cajr.util.CommonParam;
import com.cajr.vo.tag.Tag;
import com.cajr.vo.tag.TagOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author CAJR
 * @date 2020/4/20 3:07 下午
 */
@Service
public class TagNewsServiceImpl implements TagNewsService {

    @Autowired
    private TagNewsMapper tagNewsMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public TagOther getNewsList(int tagId) {
        return null;
    }

    @Override
    public void countHottestTag() {
        List<Tag> tags = this.tagService.getAll();
        Map<Integer, Integer> tagNewsNumMap = new HashMap<>(16);
        tags.forEach(tag -> {
            tagNewsNumMap.put(tag.getId(), tag.getNewsNum());
        });
        List<Map.Entry<Integer, Integer>> sortList = new ArrayList<>(tagNewsNumMap.entrySet());
        sortList.sort((o1, o2) -> o2.getValue() - o1.getValue());
        sortList.subList(0, 100);
        for (Map.Entry<Integer, Integer> m : sortList){
            this.redisTemplate.opsForZSet().add(CommonParam.HOT_TAG_REDIS_KEY, String.valueOf(m.getKey()), m.getValue());
        }
    }
}
