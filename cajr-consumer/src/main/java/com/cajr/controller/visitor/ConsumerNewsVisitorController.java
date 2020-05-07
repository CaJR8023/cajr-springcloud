package com.cajr.controller.visitor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cajr.service.INewsClientService;
import com.cajr.service.IUserClientService;
import com.cajr.util.Result;
import com.cajr.vo.user.User;
import com.cajr.vo.user.UserOther;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/4/6 9:03 下午
 */
@RestController
@RequestMapping("/visitor")
public class ConsumerNewsVisitorController {
    @Autowired
    private INewsClientService iNewsClientService;

    @Autowired
    private IUserClientService iUserClientService;

    @GetMapping("/newest")
    public PageInfo getNewestNews(@RequestParam(value = "page",defaultValue = "1") int page,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.iNewsClientService.getNewestNews(page, pageSize);
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取用户接口",httpMethod = "GET",nickname = "getOneUserVisitor")
    public Result getOneUser(@RequestParam("id")@ApiParam(value = "用户id",required = true,type = "integer" ,example = "1") int id ){
        User user = this.iUserClientService.getUser(id);
        UserOther userOther = new UserOther();
        userOther.setId(user.getId());
        userOther.setUsername(user.getUsername());
        if (user.getUserInfo() != null){
            userOther.setSignature(user.getUserInfo().getSignature());
            userOther.setAvatar(user.getUserInfo().getAvatar());
        }
        return new Result<>(userOther);
    }
}
