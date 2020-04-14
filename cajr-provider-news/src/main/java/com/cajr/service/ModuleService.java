package com.cajr.service;

import com.cajr.vo.news.Module;
import com.cajr.vo.news.ModuleCountResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/1/17 3:54 下午
 */
public interface ModuleService {

    Integer add(Module module);

    Integer addList(List<Module> modules);

    List<Module> findAll();

    List<ModuleCountResult> findAllModuleAndNews();

    PageInfo getAllByPage(int page, int pageSize);
}
