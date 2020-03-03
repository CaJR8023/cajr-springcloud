package com.cajr.vo.user;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author CAJR
 * @date 2020/3/3 11:45 上午
 */
public class UserLog {
    private Integer id;

    private Integer userId;

    private String lastLoginIp;

    private Timestamp latestLoginTime;

    private int status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Timestamp getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(Timestamp latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
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

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}