package com.cajr.lock.impl;

import com.cajr.lock.DistributedLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author CAJR
 * @date 2019/12/18 9:52 上午
 */
public class RedisDistributedLock implements DistributedLock {
    private static final Logger log = LoggerFactory.getLogger(RedisDistributedLock.class);

    private RedisTemplate redisTemplate;

    private String lockKey;

    private int expireTime;

    /**
     * 锁等待，防止线程饥饿
     */
    private int acquireTime = 1000;

    public RedisDistributedLock(RedisTemplate redisTemplate, String lockKey, int expireTime) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.expireTime = expireTime;
    }

    @Override
    public String acquireLock() {
        try {
            long end =System.currentTimeMillis()+acquireTime;

            //随机生成一个value
            String lockLogo = UUID.randomUUID().toString();
            while(System.currentTimeMillis() < end){
                boolean isLock = redisTemplate.opsForValue().setIfAbsent(lockKey,lockLogo,expireTime, TimeUnit.SECONDS);
                if (isLock){
                    return lockLogo;
                }
            }
        }catch (Exception e){
            log.error("acquire lock due to error",e);
        }
        return null;
    }

    @Override
    public boolean releaseLock(String lockLogo) {
        if (lockLogo == null){
            return false;
        }
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script,Long.class);
        redisScript.setScriptText(script);
        List<String> keys = new ArrayList<>();
        keys.add(lockKey);

        Object execute = redisTemplate.execute(redisScript,keys,lockLogo);
        if (execute == null){
            return false;
        }
        return Integer.valueOf(execute.toString()).equals(1);
    }
}
