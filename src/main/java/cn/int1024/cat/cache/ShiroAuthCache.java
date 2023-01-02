package cn.int1024.cat.cache;

import cn.int1024.cat.common.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * @Description: shiro缓存
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/6 14:43
 * @Version: 1.0
 */
@Slf4j
public class ShiroAuthCache<k, v> implements Cache<k, v> {

    private final String CACHE_NAME;

    public ShiroAuthCache(String cacheName) {
        this.CACHE_NAME = cacheName;
    }

    @Override
    public v get(k k) throws CacheException {
        if(Objects.isNull(k)) {
            return null;
        }
        return (v) getRedisTemplate().opsForHash().get(this.CACHE_NAME, k.toString());
    }

    @Override
    public v put(k k, v v) throws CacheException {
        getRedisTemplate().opsForHash().put(this.CACHE_NAME, k.toString(), v);
        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        return (v) getRedisTemplate().opsForHash().delete(this.CACHE_NAME, k.toString());
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().delete(this.CACHE_NAME);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.CACHE_NAME).intValue();
    }

    @Override
    public Set<k> keys() {
        return (Set<k>) getRedisTemplate().opsForHash().keys(this.CACHE_NAME);
    }

    @Override
    public Collection<v> values() {
        return (Collection<v>) getRedisTemplate().opsForHash().values(this.CACHE_NAME);
    }

    private RedisTemplate<String, v> getRedisTemplate() {
        RedisTemplate<String, v> redisTemplate = (RedisTemplate<String, v>) ApplicationContextUtil.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
