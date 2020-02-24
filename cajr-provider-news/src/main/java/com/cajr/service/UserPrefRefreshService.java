package com.cajr.service;

/**
 * @Author CAJR
 * @create 2020/2/20 13:54
 */
public interface UserPrefRefreshService {

    /**
     * 刷新用户喜好关键词分数
     * @return 成功：1  失败： 2
     */
    public int refresh();

    /**
     * 所有用户的喜好关键词列表TFIDF值随时间进行自动衰减更新
     * @return 成功：1  失败： 2
     */
    public int autoDecRefresh();

}
