package com.cajr.mapper;

import com.cajr.vo.user.SpecialColumnUser;

/**
 * @author CAJR
 * @date 2020/4/6 9:17 下午
 */
public interface SpecialColumnUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecialColumnUser record);

    int insertSelective(SpecialColumnUser record);

    SpecialColumnUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecialColumnUser record);

    int updateByPrimaryKey(SpecialColumnUser record);
}