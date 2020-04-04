package com.cajr.controller.user;

import com.cajr.service.IUserClientService;
import com.cajr.util.Result;
import com.cajr.vo.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author CAJR
 * @date 2019/11/26 6:19 下午
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口",value = "提供增删改查 rest接口")
public class ConsumerUserController {

    @Resource
    IUserClientService iUserClientService;

    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户接口",httpMethod = "GET",nickname = "getOneUser")
    public Result getOneUser(@PathVariable("id")@ApiParam(value = "用户id",required = true,type = "integer" ,example = "1") int id ){
        return this.iUserClientService.getOneUser(id);
    }

    @PostMapping("/")
    @ApiOperation(value = "添加用户接口",httpMethod = "POST",nickname = "addOneUser")
    public Result addOneUser(@RequestBody @ApiParam(value = "",required = true,type = "string") User user){

        return this.iUserClientService.addOneUser(user);
    }
}
