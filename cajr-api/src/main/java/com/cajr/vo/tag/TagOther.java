package com.cajr.vo.tag;

import com.cajr.vo.news.News;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 3:04 下午
 */
public class TagOther {
    private Integer id;

    private String name;

    private List<News> newsList;

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

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
