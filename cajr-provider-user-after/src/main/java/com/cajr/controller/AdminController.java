package com.cajr.controller;

import com.cajr.sevice.AdminService;
import com.cajr.util.Result;
import com.cajr.vo.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/13 9:18 下午
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/")
    public Result addOne(@RequestBody Admin admin){
        return new Result<>(this.adminService.addOneAdmin(admin));
    }


    @GetMapping("/getInfo")
    public Result getAdminInfo(@RequestParam("account") String account){
        Admin admin = this.adminService.getInfo(account);
        List<String> roles = new ArrayList<>();
        fillMockRoles(roles);
        admin.setRoles(roles);
        return new Result<>(admin);
    }

    /**
     *
     * @param roles
     */
    private void fillMockRoles(List<String> roles){
        roles.add("Home");
        roles.add("Dashbord");
        roles.add("Driver");
        roles.add("Driver-index");
        roles.add("RecTable");
        roles.add("RecommendTable");
        roles.add("NewsModuleTable");
        roles.add("NewsTable");
        roles.add("NewsLogTable");
        roles.add("NewsImgTable");
        roles.add("NewsTagTable");
        roles.add("ModuleTable");
        roles.add("UserTable");
        roles.add("RecommendTable");
        roles.add("ColumnTable");
        roles.add("RecommendTable");
        roles.add("*404");
    }
}
