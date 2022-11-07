package cn.int1024.cat.security;

import cn.int1024.cat.common.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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
        log.debug("get {}", k.toString());
        return (v) getRedisTemplate().opsForHash().get(this.cacheName, k.toString());
    }

    @Override
    public v put(k k, v v) throws CacheException {
        log.debug("put {} {}", k.toString(), v);
        getRedisTemplate().opsForHash().put(this.cacheName, k.toString(), v);
        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        log.debug("remove {}", k.toString());
        return (v) getRedisTemplate().opsForHash().delete(this.cacheName, k.toString());
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
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtil.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
