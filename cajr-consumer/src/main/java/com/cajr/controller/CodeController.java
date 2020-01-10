package com.cajr.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/1/10 3:20 下午
 */
@RestController
@RequestMapping("/code")
@Api(tags = "用户接口",value = "提供增删改查 rest接口")
public class CodeController {
}
