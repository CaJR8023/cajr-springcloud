package com.cajr.mapper;

import com.cajr.vo.news.Module;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/1/17 4:58 下午
 */
public interface ModuleMapper {
    int deleteByPrimaryKey(int id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

    List<Module> findAll();
}