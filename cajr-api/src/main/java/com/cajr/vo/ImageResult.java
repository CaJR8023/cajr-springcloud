package com.cajr.vo;

/**
 * @author CAJR
 * @date 2020/4/3 11:44 上午
 */
public class ImageResult {

    private String url;

    private String code;

    public ImageResult(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
