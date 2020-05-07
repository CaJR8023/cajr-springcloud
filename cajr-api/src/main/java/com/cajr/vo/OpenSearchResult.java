package com.cajr.vo;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/5/6 2:06 下午
 */
public class OpenSearchResult {
    private Long total;

    private Long num;

    private Long viewtotal;

    private List<OpenSearchField> items;

    private List<OpenSearchFacet> facet;

    private String error;

    private String status;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getViewtotal() {
        return viewtotal;
    }

    public void setViewtotal(Long viewtotal) {
        this.viewtotal = viewtotal;
    }

    public List<OpenSearchField> getItems() {
        return items;
    }

    public void setItems(List<OpenSearchField> items) {
        this.items = items;
    }

    public List<OpenSearchFacet> getFacet() {
        return facet;
    }

    public void setFacet(List<OpenSearchFacet> facet) {
        this.facet = facet;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
