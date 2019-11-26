package com.cajr.mapper;

import com.cajr.vo.admin.Admin;

/**
 * @author CAJR
 * @date 2019/11/26 11:54 上午
 */
public interface AdminMapper {
    Admin getOneAdminByAccount(String account);
}
