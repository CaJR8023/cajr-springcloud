package com.cajr.vo;

/**
 * @author CAJR
 * @date 2020/5/6 11:14 上午
 */
public class OpenSearchFormat<T> {

    private T fields;

    private String cmd;

    public T getFields() {
        return fields;
    }

    public void setFields(T fields) {
        this.fields = fields;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
