package com.cajr.controller.user;

import com.cajr.service.ICodeClientService;
import com.cajr.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/1/10 3:20 下午
 */
@RestController
@RequestMapping("/code")
@Api(tags = "用户接口",value = "提供增删改查 rest接口")
public class ConsumerCodeController {

    @Autowired
    ICodeClientService iCodeClientService;

    @ApiOperation(value = "获取手机验证码", httpMethod = "GET", nickname = "sendCode")
    @GetMapping("/send")
    public Result sendCode(@RequestParam("mobile") @ApiParam(value = "手机号",required = true,type = "string") String mobile,
                           @RequestParam("type") String type){
        return this.iCodeClientService.sendCode(mobile, type);
    }

    @ApiOperation(value = "验证手机验证码", httpMethod = "GET", nickname = "verifyCode")
    @GetMapping("/verify")
    public Result verifyCode(@RequestParam("tel") @ApiParam(value = "手机号",required = true,type = "string") String mobile,
                             @RequestParam("regCode") @ApiParam(value = "要验证的验证码",required = true,type = "string") String code,
                             @RequestParam("type") String type){
        return this.iCodeClientService.verifyCode(mobile,code, type);
    }
}
