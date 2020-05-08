package com.cajr.controller.reply;

import com.cajr.service.INewsClientService;
import com.cajr.util.Result;
import com.cajr.vo.news.Reply;
import com.cajr.vo.news.Review;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/5/8 8:53 下午
 */
@RestController
@RequestMapping("/reply")
@Api(tags = "回复接口",value = "回复 rest接口")
public class ConsumerReplyController {

    @Autowired
    private INewsClientService iNewsClientService;

    @PostMapping("/")
    public Result addOneReview(@RequestBody Reply r){
        return this.iNewsClientService.addOneReply(r);
    }
}
