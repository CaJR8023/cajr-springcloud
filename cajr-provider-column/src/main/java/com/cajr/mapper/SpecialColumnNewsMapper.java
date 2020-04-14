package com.cajr.mapper;

import com.cajr.vo.user.SpecialColumnNews;

/**
 * @author CAJR
 * @date 2020/4/6 9:17 下午
 */
public interface SpecialColumnNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecialColumnNews record);

    int insertSelective(SpecialColumnNews record);

    SpecialColumnNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecialColumnNews record);

    int updateByPrimaryKey(SpecialColumnNews record);
}