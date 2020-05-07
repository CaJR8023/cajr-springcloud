package com.cajr.vo.news;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author CAJR
 * @date 2020/5/6 11:08 上午
 */
public class NewsSearch implements Serializable {

    private static final long serialVersionUID = 8730998160716262096L;

    private int news_id;

    private String title;

    private String content;

    private Timestamp created_at;

    public NewsSearch(News news) {
        this.news_id = news.getId();
        this.title = news.getTitle();
        this.content = news.getContent();
        this.created_at = news.getCreatedAt();
    }

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
}
