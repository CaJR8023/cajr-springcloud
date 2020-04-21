package com.cajr.vo.news;

import java.io.Serializable;

/**
 * @author CAJR
 * @date 2020/4/13 7:23 下午
 */
public class ModuleCountResult implements Serializable {

    public ModuleCountResult() {
    }

    private static final long serialVersionUID = -8158207873944440756L;
    private String name;

    private int value;

    public ModuleCountResult(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
