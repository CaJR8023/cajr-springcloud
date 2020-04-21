package com.cajr.service;

import com.cajr.vo.user.SpecialColumn;

/**
 * @author CAJR
 * @date 2020/4/20 4:51 下午
 */
public interface ColumnService {
    SpecialColumn getONeById(Integer id);

    void countColumnAttention();
}
