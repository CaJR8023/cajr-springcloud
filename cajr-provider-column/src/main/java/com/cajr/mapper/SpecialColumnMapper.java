package com.cajr.mapper;

import com.cajr.vo.user.SpecialColumn;

/**
 * @author CAJR
 * @date 2020/4/6 9:17 下午
 */
public interface SpecialColumnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecialColumn record);

    int insertSelective(SpecialColumn record);

    SpecialColumn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecialColumn record);

    int updateByPrimaryKey(SpecialColumn record);
}