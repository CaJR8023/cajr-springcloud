package com.cajr.vo.permission;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author CAJR
 * @date 2019/11/25 7:33 下午
 */
public class Permission {
    private Integer id;

    private String name;

    private String url;

    /**
    * 请求方式 1:post 2:get 3:update 4:delete
    */
    private Integer httpMethod;

    /**
    * 1 允许权限 2 拒绝权限
    */
    private Integer type;

    private String description;

    private Integer status;

    private Timestamp createdAt;

    private Date updatedAt;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(Integer httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}