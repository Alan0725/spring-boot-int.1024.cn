package cn.int1024.cat.security;

import cn.int1024.cat.common.redis.RedisCache;
import cn.int1024.cat.common.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * @Description: shiro缓存
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/6 14:43
 * @Version: 1.0
 */
@Slf4j
public class ShiroRedisCache<k, v> implements Cache<k, v> {

    private String cacheName;

    public ShiroRedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public v get(k k) throws CacheException {
        String key = getKey(k);
        log.debug("get {}", key);
        return (v) getRedisTemplate().opsForHash().get(this.cacheName, key);
    }

    @Override
    public v put(k k, v v) throws CacheException {
        String key = getKey(k);
        log.debug("put {} {}", key, v);
        getRedisTemplate().opsForHash().put(this.cacheName, key, v);
        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        String key = getKey(k);
        log.debug("remove {}", key);
        return (v) getRedisTemplate().opsForHash().delete(this.cacheName, key);
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<k> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<v> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }

    private RedisTemplate getRedisTemplate(){
        RedisCache redisCache = (RedisCache) ApplicationContextUtil.getBean("redisCache");
        return redisCache.redisTemplate;
    }

    private String getKey(k k) {
        return k.toString();
    }

    private boolean isUserStr(k k) {
        return k.toString().startsWith("User(") && k.toString().endsWith(")");
    }

    private String getUsernameByK(k k) {
        String str = k.toString();
        String str1 = str.substring(0, str.indexOf("username="));
        String str2 = str.substring(str1.length()+9);
        return str2.substring(0, str2.indexOf(","));
    }
}
