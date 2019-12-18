package com.cajr.lock;

/**
 * @author CAJR
 * @date 2019/12/18 9:51 上午
 */
public interface DistributedLock {

    String acquireLock();

    boolean releaseLock(String lockLogo);

}
