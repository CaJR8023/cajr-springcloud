package com.cajr.controller.news;

import com.cajr.service.IModuleService;
import com.cajr.util.Result;
import com.cajr.vo.news.Module;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/13 9:02 下午
 */
@RestController
@RequestMapping("/module")
@Api(tags = "模块接口",value = "模块 rest接口")
public class ConsumerModuleController {

    @Autowired
    private IModuleService iModuleService;

    @GetMapping("/")
    public List<Module> getAllModule(){
        return this.iModuleService.getAllModule();
    }

    @GetMapping("/count")
    public Result getAllModuleAndNews(){
        return this.iModuleService.getAllModuleAndNews();
    }

    @GetMapping("/page")
    public PageInfo getAllByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "pageSize", defaultValue = "8") int pageSize){
        return this.iModuleService.getAllByPage(page, pageSize);
    }
}
