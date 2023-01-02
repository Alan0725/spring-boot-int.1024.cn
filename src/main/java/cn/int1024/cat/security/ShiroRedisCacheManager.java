package cn.int1024.cat.security;

import cn.int1024.cat.cache.ShiroAuthCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Description: Redis缓存
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/6 14:40
 * @Version: 1.0
 */
@Slf4j
public class ShiroRedisCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        if(cacheName.contains("authorizationCache")) {
            cacheName = "CatAuthorizationCache";
        }
        return new ShiroAuthCache<>(cacheName);
    }
}
