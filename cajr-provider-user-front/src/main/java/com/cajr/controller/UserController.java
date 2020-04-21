package com.cajr.controller;

import com.cajr.service.CodeService;
import com.cajr.service.UserService;
import com.cajr.util.Result;
import com.cajr.vo.user.User;
import com.cajr.vo.user.UserOther;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author CAJR
 * @date 2019/11/26 2:05 下午
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口",value = "提供增删改查 rest接口")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CodeService codeService;

    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户接口",httpMethod = "GET",nickname = "getOneUser")
    public Result getOneUser(@PathVariable("id")@ApiParam(value = "用户id",required = true,type = "integer" ,example = "1") int id ){
        if ( this.userService.checkIsExistById(id).get() <= 0){
            System.out.println(this.userService.checkIsExistById(id).get());
            return new Result<>("用户不存在","");
        }
        Optional<User> result = this.userService.getOneUser(id);
        return new Result<>(result.get());
    }

    @GetMapping("/user_other")
    public UserOther getOneUserOther(@RequestParam("userId")@ApiParam(value = "用户id",required = true,type = "integer" ,example = "1") int userId ){
        return this.userService.getOneUserOther(userId);
    }

    @GetMapping("/one")
    @ApiOperation(value = "获取用户接口",httpMethod = "GET",nickname = "getUser")
    public User getUser(@RequestParam("userId")@ApiParam(value = "用户手机号",required = true,type = "integer" ,example = "1") int userId ){
        if ( this.userService.checkIsExistById(userId).get() <= 0){
            System.out.println(this.userService.checkIsExistById(userId).get());
        }
        return this.userService.getUser(userId);
    }

    @GetMapping("/by_name")
    @ApiOperation(value = "获取用户接口",httpMethod = "GET",nickname = "getUserByTel")
    public Result getUserByTel(@RequestParam("tel")@ApiParam(value = "用户id",required = true,type = "integer" ,example = "1") String tel ){
        if ( this.userService.checkIsExistByTel(tel).get() <= 0){
            return new Result<>("用户不存在","");
        }
        return new Result<>(this.userService.getUserByTel(tel));
    }

    @PostMapping("/")
    @ApiOperation(value = "添加用户接口",httpMethod = "POST",nickname = "addOneUser")
    public Result addOneUser(@RequestBody @ApiParam(value = "",required = true,type = "string") User user){
        if (this.userService.checkIsExistByTel(user.getTel()).get() >= 1){
            return new Result<>("该手机号码已注册！","");
        }
        if (this.userService.checkIsExistByUserName(user.getUsername()).get() >= 1){
            return new Result<>("该用户名已注册！","");
        }
            Optional<Integer> result = this.userService.add(user);
        if (!result.isPresent()){
            return new Result<>("添加失败","");
        }
        return new Result<>(result.get());
    }

    @PostMapping("/news_init")
    public Result addOneUserNewsInit(@RequestBody @ApiParam(value = "",required = true,type = "string") User user){
        if (this.userService.checkIsExistByUserName(user.getUsername()).get() >= 1){
            return new Result<>(this.userService.getUserByUserName(user.getUsername()).getId());
        }
        Optional<Integer> result = this.userService.add(user);
        if (!result.isPresent()){
            return new Result<>("添加失败","");
        }
        return new Result<>(result.get());
    }


    @GetMapping("/all_user_id")
    public List<Integer> findAllUserId(){
        return this.userService.findAllUserId();
    }

    @GetMapping("/active")
    public List<Integer> findActiveUserId(){
        return this.userService.findAllActiveUserId();
    }

    @GetMapping("/section")
    public List<User> findSectionUser(@RequestParam("userIds") List<Integer> userIds){
        if (userIds.isEmpty()){
            return null;
        }
        return this.userService.findSection(userIds);
    }
    @GetMapping("/page")
    public PageInfo getAllByPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return this.userService.getAllByPage(page, pageSize);
    }

}
