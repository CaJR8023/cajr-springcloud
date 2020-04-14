package com.cajr.vo.news;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/13 8:50 下午
 */
public class CountNewsRecommendResult {

    private List<Integer> CB;

    private List<Integer> CF;

    private List<Integer> HR;

    public CountNewsRecommendResult() {

    }

    public CountNewsRecommendResult(List<Integer> CB, List<Integer> CF, List<Integer> HR) {
        this.CB = CB;
        this.CF = CF;
        this.HR = HR;
    }

    public List<Integer> getCB() {
        return CB;
    }

    public void setCB(List<Integer> CB) {
        this.CB = CB;
    }

    public List<Integer> getCF() {
        return CF;
    }

    public void setCF(List<Integer> CF) {
        this.CF = CF;
    }

    public List<Integer> getHR() {
        return HR;
    }

    public void setHR(List<Integer> HR) {
        this.HR = HR;
    }
}
