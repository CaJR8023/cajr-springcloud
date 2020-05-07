package com.cajr.vo.user;

import java.io.Serializable;

/**
 * @author CAJR
 * @date 2020/5/6 11:32 上午
 */
public class UserSearch implements Serializable {

    private static final long serialVersionUID = 7806732976548287339L;

    private int user_id;

    private String user_name;

    public UserSearch(User user) {
        this.user_id = user.getId();
        this.user_name = user.getUsername();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
