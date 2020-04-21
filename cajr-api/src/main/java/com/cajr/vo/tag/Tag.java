package com.cajr.vo.tag;

import com.cajr.vo.news.News;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/16 9:55 上午
 */
public class Tag implements Serializable {
    private Integer id;

    private String name;

    private Integer status;

    @ApiModelProperty(hidden = true)
    private Timestamp createdAt;

    @ApiModelProperty(hidden = true)
    private Timestamp updatedAt;

    @ApiModelProperty(hidden = true)
    private Integer newsNum;

    @ApiModelProperty(hidden = true)
    private List<News> newsList;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private List<Integer> newsIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Integer getNewsNum() {
        return newsNum;
    }

    public void setNewsNum(Integer newsNum) {
        this.newsNum = newsNum;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public List<Integer> getNewsIds() {
        return newsIds;
    }

    public void setNewsIds(List<Integer> newsIds) {
        this.newsIds = newsIds;
    }
}