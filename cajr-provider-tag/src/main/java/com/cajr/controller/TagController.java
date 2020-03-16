package com.cajr.controller;

import com.cajr.service.TagService;
import com.cajr.util.Result;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/3/16 10:14 上午
 */
@RestController
@RequestMapping("/tag")
@Api(tags = "标签接口",value = "提供增删改查 rest接口")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/name")
    public Tag getOneByName(@RequestParam("name") String name){
       return this.tagService.getOneTagByName(name);
    }

    @GetMapping("/{id}")
    public Tag getOneById(@PathVariable("id") int id){
        return this.tagService.getOneTagById(id);
    }

    @PostMapping("/")
    public Result addOneTag(@RequestBody Tag tag){
        if (this.tagService.checkTagIsExistedByName(tag.getName()) > 0){
            return new Result<>("标签已存在！",-1);
        }
        Integer result = this.tagService.addOneTag(tag);

        return new Result<>(result);
    }
}
