package com.cajr.vo.user;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * @author CAJR
 * @date 2020/4/21 4:35 下午
 */
public class UserLikeReply {
    private Integer id;

    private Integer replyId;

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

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
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