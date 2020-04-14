package com.cajr.vo.news;

/**
 * @author CAJR
 * @date 2020/4/13 7:23 下午
 */
public class ModuleCountResult {

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
