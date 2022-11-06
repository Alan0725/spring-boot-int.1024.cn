package cn.int1024.cat.security;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Description: Redis缓存
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/6 14:40
 * @Version: 1.0
 */
public class ShiroRedisCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new ShiroRedisCache<>(cacheName);
    }
}
