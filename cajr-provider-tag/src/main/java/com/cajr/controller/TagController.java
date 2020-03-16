package com.cajr.controller;

import com.cajr.service.TagService;
import com.cajr.util.Result;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/3/16 10:14 上午
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;



    @PostMapping("/")
    public Result addOneTag(@RequestBody Tag tag){
        if (this.tagService.checkTagIsExistedByName(tag.getName()) > 0){
            return new Result<>("标签已存在！",-1);
        }
        Integer result = this.tagService.addOneTag(tag);

        return new Result<>(result);
    }

    @PostMapping("/module_tag")
    public Result addOneTag(@RequestBody ModuleTag moduleTag){
        if (this.tagService.checkIsExistedByModuleIdAndTagId(moduleTag.getModuleId(), moduleTag.getTagId()) > 0){
            return new Result<>("模块标签对已存在！",-1);
        }
        Integer result = this.tagService.addOneModuleTag(moduleTag);

        return new Result<>(result);
    }

    @PostMapping("/news_tag")
    public Result addOneTag(@RequestBody NewsTag newsTag){
        if (this.tagService.checkIsExistedByNewsIdAndTagId(newsTag.getNewsId(), newsTag.getTagId()) > 0){
            return new Result<>("新闻标签已存在！",-1);
        }
        Integer result = this.tagService.addOneNewsTag(newsTag);
        return new Result<>(result);
    }
}
