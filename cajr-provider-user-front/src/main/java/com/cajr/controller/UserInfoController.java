package com.cajr.controller;

import com.cajr.service.UserInfoService;
import com.cajr.util.Result;
import com.cajr.vo.user.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/5/7 4:57 下午
 */
@RestController
@RequestMapping("/user_info")
@Api(tags = "用户信息接口",value = "提供增删改查 rest接口")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;


    @PutMapping("/")
    public Result update(@RequestBody UserInfo userInfo){
        return new Result<>(this.userInfoService.update(userInfo));
    }
}
