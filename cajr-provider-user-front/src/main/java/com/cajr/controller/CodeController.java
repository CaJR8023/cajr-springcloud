package com.cajr.controller;

import com.cajr.service.CodeService;
import com.cajr.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CAJR
 * @date 2020/1/10 3:31 下午
 */
@RestController
@RequestMapping("/code")
@Api(tags = "验证码",value = "验证码 rest接口")
public class CodeController {
    @Resource
    CodeService codeService;

    @GetMapping("/send")
    public Result sendCode(@RequestParam("code") @ApiParam(value = "验证码",required = true,type = "string") String code,
                           @RequestParam("phone") @ApiParam(value = "手机号",required = true,type = "string") String phone){
        return new Result<>(this.codeService.sendCode(code,phone));
    }
}
