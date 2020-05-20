package com.panghu.reids;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.reids
 * @ClassName: RedisCacheManager
 * @Author: wxy
 * @Description: redis缓存
 * @Date: 2020/5/18 23:36
 * @Version: 1.0
 */
public class RedisCacheManager implements CacheManager {

    private final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

    /**
     * fast lookup by name map
     */
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
    private RedisManager redisManager;
    /**
     * expire time in seconds
     */
    private static final int DEFAULT_EXPIRE = 1800;
    private int expire = DEFAULT_EXPIRE;

    /**
     * The Redis key prefix for caches
     */
    public static final String DEFAULT_CACHE_KEY_PREFIX = "shiro:cache:";
    private String keyPrefix = DEFAULT_CACHE_KEY_PREFIX;
    public static final String DEFAULT_PRINCIPAL_ID_FIELD_NAME = "authCacheKey or id";
    private String principalIdFieldName = DEFAULT_PRINCIPAL_ID_FIELD_NAME;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("get cache, name={}",name);
        Cache cache = caches.get(name);
        if (cache == null) {
            cache = new RedisCache<K, V>(redisManager,keyPrefix + name + ":", expire, principalIdFieldName);
            caches.put(name, cache);
        }
        return cache;
    }

    public Logger getLogger() {
        return logger;
    }

    public ConcurrentMap<String, Cache> getCaches() {
        return caches;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    public static int getDefaultExpire() {
        return DEFAULT_EXPIRE;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public static String getDefaultCacheKeyPrefix() {
        return DEFAULT_CACHE_KEY_PREFIX;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public static String getDefaultPrincipalIdFieldName() {
        return DEFAULT_PRINCIPAL_ID_FIELD_NAME;
    }

    public String getPrincipalIdFieldName() {
        return principalIdFieldName;
    }

    public void setPrincipalIdFieldName(String principalIdFieldName) {
        this.principalIdFieldName = principalIdFieldName;
    }
}
