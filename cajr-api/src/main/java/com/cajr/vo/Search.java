package com.cajr.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author CAJR
 * @date 2020/5/6 2:59 下午
 */
public class Search implements Serializable {
    private static final long serialVersionUID = -1311136948215416504L;

    private int news_id;

    private String title;

    private String content;

    private Timestamp created_at;

    private int user_id;

    private String user_name;

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
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
