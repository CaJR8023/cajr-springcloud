package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.ITagClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author CAJR
 * @date 2020/3/16 11:20 上午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = ITagClientServiceFallBackFactory.class)
public interface ITagClientService {
    @PostMapping("/tag/")
    public Result addOneTag(@RequestBody Tag tag);

    @PostMapping("/tag/module_tag")
    public Result addOneTag(@RequestBody ModuleTag moduleTag);

    @PostMapping("/tag/news_tag")
    public Result addOneTag(@RequestBody NewsTag newsTag);
}
