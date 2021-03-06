package com.cajr.service.fallbak;

import com.cajr.service.ITagClientService;
import com.cajr.util.Result;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import com.github.pagehelper.PageInfo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/16 11:21 上午
 */
@Component
public class ITagClientServiceFallBackFactory implements FallbackFactory<ITagClientService> {
    @Override
    public ITagClientService create(Throwable throwable) {
        return new ITagClientService() {
            @Override
            public Tag getOneByName(String name) {
                return new Tag();
            }

            @Override
            public Tag getOneById(int id) {
                return new Tag();
            }

            @Override
            public Result addOneTag(Tag tag) {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public PageInfo getAllTag(int page, int pageSize) {
                return null;
            }

            @Override
            public Result countHottestTag() {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public Result getHottestTags() {
                return new Result<>("hystrix fail",0);
            }

            @Override
            public List<Tag> getNewsTag(Integer newsId) {
                List<Tag> tags = new ArrayList<>();
                Tag tag = new Tag();
                tag.setName("hystrix");
                tag.setId(0);
                tags.add(tag);
                return tags;
            }
        };
    }
}
