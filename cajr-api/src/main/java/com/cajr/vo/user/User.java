package com.cajr.vo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author CAJR
 * @date 2019/11/25 7:22 下午
 */
public class User implements Serializable {
    private static final long serialVersionUID = 5736691562737169035L;
    private int id;

    private String username;

    private String tel;

    private String password;

    private int status;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Timestamp createdAt;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Timestamp updatedAt;

    @ApiModelProperty(hidden = true)
    private UserLog userLog;

    @ApiModelProperty(hidden = true)
    private UserPref userPref;

    @ApiModelProperty(hidden = true)
    private UserInfo userInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserLog getUserLog() {
        return userLog;
    }

    public void setUserLog(UserLog userLog) {
        this.userLog = userLog;
    }

    public UserPref getUserPref() {
        return userPref;
    }

    public void setUserPref(UserPref userPref) {
        this.userPref = userPref;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}