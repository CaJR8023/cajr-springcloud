package com.cajr.service;

/**
 * @author CAJR
 * @date 2020/1/10 3:32 下午
 */
public interface CodeService {
    Integer sendCode(String phone, String type);

    Integer checkCode(String code, String phone, String type);
}
