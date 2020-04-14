package com.cajr.sevice;

import com.cajr.vo.admin.Admin;

/**
 * @author CAJR
 * @date 2020/4/13 9:19 下午
 */
public interface AdminService {
    public Integer addOneAdmin(Admin admin);

    Admin  getInfo(String account);
}
