package com.cajr.service;

import java.util.List;

/**
 * @author CAJR
 * @date 2020/3/3 6:56 下午
 */
public interface UserPrefRefresherService {

    public void refresh();

    public void refreshSpecificUser(List<Integer> userIds);

    public void autoDecRefresh();
}
