package com.cajr.service;

import com.cajr.config.FeignClientConfig;
import com.cajr.service.fallbak.IAdminClientServiceFallBackFactory;
import com.cajr.util.Result;
import com.cajr.vo.admin.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CAJR
 * @date 2020/4/13 9:15 下午
 */
@FeignClient(name = "cajr-provider-basic-data",configuration = FeignClientConfig.class,
        fallbackFactory = IAdminClientServiceFallBackFactory.class)
public interface IAdminClientService {
    @PostMapping("/admin/")
    public Result addOneAdmin(Admin admin);

    @GetMapping("/admin/getInfo")
    public Result getAdminInfo(@RequestParam("account") String account);
}
