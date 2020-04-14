package com.cajr.controller.tag;

import com.cajr.service.ITagClientService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/4/14 3:23 下午
 */
@RestController
@RequestMapping("/tag")
@Api(tags = "标签接口",value = "提供增删改查 rest接口")
public class ConsumerTagController {

    @Autowired
    private ITagClientService iTagClientService;

    @GetMapping("/")
    public PageInfo getAllTag(@RequestParam(value = "page",defaultValue = "1") int page,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.iTagClientService.getAllTag(page, pageSize);
    }
}
