package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.IModuleServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.news.Module;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/10 12:19 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = IModuleServiceFallBackFactory.class)
public interface IModuleService {

    @GetMapping("/module/")
    public List<Module> getAllModule();

    @GetMapping("/module/count")
    public Result getAllModuleAndNews();

    @GetMapping("/module/page")
    public PageInfo getAllByPage(@RequestParam("page") int page,
                                 @RequestParam("pageSize") int pageSize);
}
