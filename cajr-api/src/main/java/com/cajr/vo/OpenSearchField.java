package com.cajr.vo;

import com.cajr.vo.news.NewsSearch;

/**
 * @author CAJR
 * @date 2020/5/6 2:09 下午
 */
public class OpenSearchField {

    private Search fields;

    public Search getFields() {
        return fields;
    }

    public void setFields(Search fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "OpenSearchField{" +
                "fields=" + fields +
                '}';
    }
}
