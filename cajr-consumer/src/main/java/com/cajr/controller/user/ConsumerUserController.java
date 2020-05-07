package com.cajr.controller.user;

import com.cajr.service.IUserClientService;
import com.cajr.util.Result;
import com.cajr.vo.SearchPage;
import com.cajr.vo.user.User;
import com.github.pagehelper.PageInfo;
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



    @GetMapping("/_name")
    @ApiOperation(value = "获取用户接口",httpMethod = "GET",nickname = "getOneUser")
    public Result getOneUserByTel(@RequestParam("tel")@ApiParam(value = "用户手机号",required = true,type = "string") String tel ){
        return this.iUserClientService.getOneUserByTel(tel);
    }

    @GetMapping("/page")
    public PageInfo getAllByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.iUserClientService.getAllByPage(page, pageSize);
    }

    @PostMapping("/")
    @ApiOperation(value = "添加用户接口",httpMethod = "POST",nickname = "addOneUser")
    public Result addOneUser(@RequestBody @ApiParam(value = "",required = true,type = "string") User user){
        return this.iUserClientService.addOneUser(user);
    }

//    @PostMapping("/")
//    @ApiOperation(value = "添加用户接口",httpMethod = "POST",nickname = "addOneUser")
//    public Result addUser(@RequestParam("tel") @ApiParam(value = "用户手机号", required = true, type = "string") String tel,
//                          @RequestParam("password")@ApiParam(value = "用户密码", required = true, type = "string") String password,
//                          @RequestParam("username")@ApiParam(value = "用户名", required = true, type = "string") String userName){
//        User user = new User();
//        user.setUsername(userName);
//        user.setPassword(password);
//        user.setTel(tel);
//        user.setStatus(1);
//        return this.iUserClientService.addOneUser(user);
//    }

    @GetMapping("/visitor/search")
    public SearchPage searchUser(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "page",defaultValue = "1") int page){
        return this.iUserClientService.searchUsers(keyWord, page, 10);
    }
}
