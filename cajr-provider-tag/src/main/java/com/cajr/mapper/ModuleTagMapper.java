package com.cajr.mapper;

import com.cajr.vo.tag.ModuleTag;

/**
 * @author CAJR
 * @date 2020/3/16 9:55 上午
 */
public interface ModuleTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleTag record);

    int insertSelective(ModuleTag record);

    ModuleTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleTag record);

    int updateByPrimaryKey(ModuleTag record);

    int checkIsExistedByModuleIdAndTagId(int moduleId, int tagId);
}