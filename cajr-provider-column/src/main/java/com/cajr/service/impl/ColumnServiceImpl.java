package com.cajr.service.impl;

import com.cajr.mapper.SpecialColumnMapper;
import com.cajr.service.ColumnService;
import com.cajr.vo.user.SpecialColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CAJR
 * @date 2020/4/20 4:51 下午
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private SpecialColumnMapper specialColumnMapper;

    @Override
    public SpecialColumn getONeById(Integer id) {
        return null;
    }

    @Override
    public void countColumnAttention() {

    }
}
