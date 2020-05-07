package com.cajr.vo;

/**
 * @author CAJR
 * @date 2020/5/6 2:08 下午
 */
public class OpenSearchFacetItem {
    private String count;
    private String sum;
    private String value;
    private String max;
    private String min;

    public OpenSearchFacetItem() {
    }

    public OpenSearchFacetItem(String count, String sum, String value, String max, String min) {
        this.count = count;
        this.sum = sum;
        this.value = value;
        this.max = max;
        this.min = min;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
