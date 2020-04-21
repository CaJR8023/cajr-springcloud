package com.cajr.controller;

import com.cajr.service.UserLikeReplyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAJR
 * @date 2020/4/21 5:26 下午
 */
@RestController
@RequestMapping("/user_like_reply")
@Api(tags = "用户回复接口",value = "提供增删改查 rest接口")
public class UserLikeReplyController {

    @Autowired
    private UserLikeReplyService userLikeReplyService;
}
