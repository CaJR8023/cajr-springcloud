package com.cajr.service;

/**
 * @author CAJR
 * @date 2020/1/16 11:02 上午
 */
public interface NewsDataService {
    public String crawlChannelData() throws Exception;

    public String crawlNewsData() throws Exception;
}
