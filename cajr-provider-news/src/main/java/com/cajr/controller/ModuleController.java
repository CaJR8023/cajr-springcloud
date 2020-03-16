package com.cajr.controller;

import com.cajr.service.ModuleService;
import com.cajr.vo.news.Module;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/10 12:21 下午
 */
@RestController
@RequestMapping("/module")

@Api(tags = "新闻模块",value = "新闻模块 rest接口")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/")
    public List<Module> getAllModule(){
        return this.moduleService.findAll();
    }
}