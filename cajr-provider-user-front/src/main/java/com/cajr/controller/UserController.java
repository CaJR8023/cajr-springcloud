package com.cajr.controller;

import com.cajr.service.CodeService;
import com.cajr.service.UserService;
import com.cajr.util.Result;
import com.cajr.vo.user.User;
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

    @PostMapping("/")
    @ApiOperation(value = "添加用户接口",httpMethod = "POST",nickname = "addOneUser")
    public Result addOneUser(@RequestBody @ApiParam(value = "",required = true,type = "string") User user){
        if (this.userService.checkIsExistByTel(user.getTel()).get() >= 1){
            return new Result<>("该手机号码已注册！","");
        }
        Optional<Integer> result = this.userService.add(user);
        if (!result.isPresent()){
            return new Result<>("添加失败","");
        }
        return new Result<>(result.get());
    }


    @GetMapping("/all_user_id")
    public Result findAllUserId(){
        return new Result<>(this.userService.findAllUserId());
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

}
