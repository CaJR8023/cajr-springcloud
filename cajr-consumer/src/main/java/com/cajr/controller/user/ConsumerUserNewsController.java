package com.cajr.controller.user;

import com.cajr.service.IUserClientService;
import com.cajr.util.Result;
import com.cajr.vo.user.UserNewsStar;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/5/8 3:54 下午
 */
@RestController
@RequestMapping("/user_news_star")
@Api(tags = "点赞接口",value = "提供增删改查 rest接口")
public class ConsumerUserNewsController {

    @Autowired
    private IUserClientService iUserClientService;

    @PostMapping("/star")
    public Result star(@RequestBody UserNewsStar userNewsStar){
        return this.iUserClientService.star(userNewsStar);
    }

}
