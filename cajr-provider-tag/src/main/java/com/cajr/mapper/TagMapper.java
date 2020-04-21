package com.cajr.mapper;

import com.cajr.vo.tag.Tag;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/16 9:55 上午
 */
public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    Tag selectByPrimaryName(String name);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    int checkIsExistedByName(String name);

    List<Tag> selectAll();

    List<Tag> findAll();

    List<Tag> selectAllByIds(List<Integer> ids);
}