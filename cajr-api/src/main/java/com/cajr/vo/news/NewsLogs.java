package com.cajr.vo.news;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author CAJR
 * @date 2020/3/3 10:04 上午
 */
public class NewsLogs {
    private Integer id;

    private Integer userId;

    private Integer newsId;

    private Timestamp viewTime;

    private int preferDegree;

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

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Timestamp getViewTime() {
        return viewTime;
    }

    public void setViewTime(Timestamp viewTime) {
        this.viewTime = viewTime;
    }

    public int getPreferDegree() {
        return preferDegree;
    }

    public void setPreferDegree(int preferDegree) {
        this.preferDegree = preferDegree;
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
}