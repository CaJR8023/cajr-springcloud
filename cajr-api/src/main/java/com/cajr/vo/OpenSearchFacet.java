package com.cajr.vo;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/5/6 2:09 下午
 */
public class OpenSearchFacet {

    List<OpenSearchFacetItem> items;

    public List<OpenSearchFacetItem> getItems() {
        return items;
    }

    public void setItems(List<OpenSearchFacetItem> items) {
        this.items = items;
    }

}
