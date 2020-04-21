package com.cajr.service.impl;

import com.cajr.mapper.ModuleMapper;
import com.cajr.service.ModuleService;
import com.cajr.service.NewsService;
import com.cajr.vo.news.Module;
import com.cajr.vo.news.ModuleCountResult;
import com.cajr.vo.news.News;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/1/17 4:02 下午
 */
@Service
@CacheConfig(cacheNames = "cajr::module")
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleMapper moduleMapper;

    @Autowired
    private NewsService newsService;

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "cajr::module::count")
            }
    )
    public Integer add(Module module) {
        if (this.moduleMapper.checkExistByName(module.getName()) >= 1){
            return -1;
        }
        module.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        module.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return moduleMapper.insertSelective(module);
    }

    @Override
    public Integer addList(List<Module> modules) {
        modules.forEach(this::add);
        return 1;
    }

    @Override
    public List<Module> findAll() {
        return this.moduleMapper.findAll();
    }

    @Override
    @Cacheable(value = "cajr::module::count")
    public List<ModuleCountResult> findAllModuleAndNews() {
        List<Module> modules = this.findAll();
        List<ModuleCountResult> moduleCountResults = new ArrayList<>();
        for (Module m : modules) {
            List<News> newsList = this.newsService.findAllByModuleId(m.getId());
            ModuleCountResult moduleCountResult = new ModuleCountResult(m.getName(), newsList.size());
            moduleCountResults.add(moduleCountResult);
        }
        return moduleCountResults;
    }

    @Override
    public PageInfo getAllByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(this.moduleMapper.findAll());
    }
}
