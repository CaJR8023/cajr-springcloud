package com.cajr.controller.admin;

import com.cajr.service.IAdminClientService;
import com.cajr.service.INewsClientService;
import com.cajr.service.IUserClientService;
import com.cajr.util.Result;
import com.cajr.vo.admin.Admin;
import com.cajr.vo.news.News;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CAJR
 * @date 2020/4/13 9:21 下午
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin接口",value = "admin rest接口")
public class ConsumerAdminController {
    @Autowired
    private IAdminClientService iAdminClientService;

    @Autowired
    private IUserClientService iUserClientService;

    @Autowired
    private INewsClientService iNewsClientService;

    @PostMapping("/")
    public Result addOneAdmin(@RequestBody Admin admin){
        return this.iAdminClientService.addOneAdmin(admin);
    }

    @GetMapping("/getInfo")
    public Result getInfo(@RequestParam("account") String account){
        return this.iAdminClientService.getAdminInfo(account);
    }

    @GetMapping("/card_data")
    public Result getCardData(){
        List<Integer> userIds = this.iUserClientService.findAllUserId();
        PageInfo newsList = this.iNewsClientService.getAll(1, 10);
        Map<String, Integer> totalMap = new HashMap<>(16);
        totalMap.put("totalUserNum", userIds.size());
        totalMap.put("totalNewsNum", (int) newsList.getTotal());

        return new Result<>(totalMap);
    }
}
