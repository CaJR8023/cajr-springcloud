package com.cajr.vo.news;

import com.cajr.vo.user.User;
import com.cajr.vo.user.UserOther;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 1:18 下午
 */
public class Review {
    private Integer id;

    private Integer newsId;

    private Integer userId;

    private String content;

    private int status;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Timestamp createdAt;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Timestamp updatedAt;

    @ApiModelProperty(hidden = true)
    private List<Reply> replyList;

    @ApiModelProperty(hidden = true)
    private Integer likeNum;

    @ApiModelProperty(hidden = true)
    private Integer unlikeNum;

    @ApiModelProperty(hidden = true)
    private Integer replyNum;

    @ApiModelProperty(hidden = true)
    private UserOther userOther;

    @ApiModelProperty(hidden = true)
    private String time;

    @ApiModelProperty(hidden = true)
    private List<Integer> likeUserIds;

    @ApiModelProperty(hidden = true)
    private List<Integer> unlikeUserIds;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getUnlikeNum() {
        return unlikeNum;
    }

    public void setUnlikeNum(Integer unlikeNum) {
        this.unlikeNum = unlikeNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public UserOther getUserOther() {
        return userOther;
    }

    public void setUserOther(UserOther userOther) {
        this.userOther = userOther;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Integer> getLikeUserIds() {
        return likeUserIds;
    }

    public void setLikeUserIds(List<Integer> likeUserIds) {
        this.likeUserIds = likeUserIds;
    }

    public List<Integer> getUnlikeUserIds() {
        return unlikeUserIds;
    }

    public void setUnlikeUserIds(List<Integer> unlikeUserIds) {
        this.unlikeUserIds = unlikeUserIds;
    }
}