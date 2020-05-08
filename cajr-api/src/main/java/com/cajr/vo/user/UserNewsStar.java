package com.cajr.vo.user;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author CAJR
 * @date 2020/5/8 1:48 下午
 */
public class UserNewsStar {
    private Integer id;

    private Integer newsId;

    private Integer userId;

    private int isLike;

    private int status;

    @ApiModelProperty(hidden = true)
    private Timestamp createdAt;

    @ApiModelProperty(hidden = true)
    private Timestamp updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
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