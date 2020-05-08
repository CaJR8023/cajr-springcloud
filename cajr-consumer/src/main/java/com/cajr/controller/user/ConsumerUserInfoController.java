package com.cajr.controller.user;

import com.cajr.service.IUserClientService;
import com.cajr.util.Result;
import com.cajr.vo.user.UserInfo;
import com.cajr.vo.user.UserOther;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author CAJR
 * @date 2020/5/7 4:51 下午
 */
@RestController
@Validated
@RequestMapping("/user_info")
@Api(tags = "用户信息接口",value = "提供增删改查 rest接口")
public class ConsumerUserInfoController {

    @Resource
    private IUserClientService iUserClientService;

    @PutMapping("/")
    public Result update(@RequestBody @NotNull UserInfo userInfo){
        return this.iUserClientService.updateUserInfo(userInfo);
    }
}
