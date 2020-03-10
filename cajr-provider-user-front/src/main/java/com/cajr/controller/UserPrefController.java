package com.cajr.controller;

import com.cajr.service.UserPrefService;
import com.cajr.util.Result;
import com.cajr.vo.user.UserPref;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CAJR
 * @date 2020/3/3 6:27 下午
 */
@RestController
@RequestMapping("/user_pref")
@Api(tags = "用户喜好关键词接口",value = "提供增删改查 rest接口")
public class UserPrefController {

    @Autowired
    private UserPrefService userPrefService;

    @PostMapping("/")
    public Result addOrUpdateOne(@RequestBody UserPref userPref){

        if (this.userPrefService.checkExistUserPrefByUserId(userPref.getUserId()) > 0){
            return new Result<>(this.userPrefService.update(userPref));
        }
        return new Result<>(this.userPrefService.add(userPref));
    }

    @PutMapping("/")
    public Result update(@RequestBody UserPref userPref){
        if (this.userPrefService.checkExistUserPrefByUserId(userPref.getUserId()) <= 0){
            return new Result<>(this.userPrefService.add(userPref));
        }
        return new Result<>(this.userPrefService.update(userPref));
    }
}
