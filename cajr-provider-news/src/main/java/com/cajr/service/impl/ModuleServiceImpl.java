package com.cajr.service.impl;

import com.cajr.mapper.ModuleMapper;
import com.cajr.service.ModuleService;
import com.cajr.vo.news.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/1/17 4:02 下午
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleMapper moduleMapper;

    @Override
    public Integer add(Module module) {
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
        return null;
    }
}
