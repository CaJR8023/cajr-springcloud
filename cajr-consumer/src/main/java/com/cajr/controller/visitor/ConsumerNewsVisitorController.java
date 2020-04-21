package com.cajr.controller.visitor;

import com.cajr.service.INewsClientService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/4/6 9:03 下午
 */
@RestController
@RequestMapping("/visitor")
public class ConsumerNewsVisitorController {
    @Autowired
    private INewsClientService iNewsClientService;

    @GetMapping("/newest")
    public PageInfo getNewestNews(@RequestParam(value = "page",defaultValue = "1") int page,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.iNewsClientService.getNewestNews(page, pageSize);
    }
}
