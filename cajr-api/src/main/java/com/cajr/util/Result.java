package com.cajr.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author CAJR
 * @date 2019/11/25 4:15 下午
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    private String msg = "success";

    @Setter
    @Getter
    private T data;

    public Result() {
        super();
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }
}
