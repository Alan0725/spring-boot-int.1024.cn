package cn.int1024.cat.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: RedisService实现
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/12 22:30
 * @Version: 1.0
 */
@Slf4j
@Component
@SuppressWarnings({"unused", "unchecked"})
public class RedisService<T> {

    @Resource
    private RedisTemplate<String, T> redisTemplate;

    /**
     * 给一个指定的 key 值附加过期时间, 默认秒
     *
     * @param key  键
     * @param time 过期时间
     */
    public Boolean expire(String key, long time) {
        return expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 给一个指定的 key 值附加过期时间
     *
     * @param key  键
     * @param time 过期时间
     */
    public Boolean expire(String key, long time, TimeUnit timeUnit) {
        if (redisTemplate != null) {
            return redisTemplate.expire(key, time, timeUnit);
        }
        return false;

    }

    /**
     * 根据key 获取过期时间 秒
     *
     * @param key 键
     */
    public Long getTime(String key) {
        if (redisTemplate != null) {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        }
        return null;

    }

    /**
     * 是否存在key
     *
     * @param key 键
     */
    public Boolean hasKey(String key) {
        if (redisTemplate != null) {
            return redisTemplate.hasKey(key);
        }
        return false;

    }

    /**
     * 移除给定 key 的过期时间，使得 key 永不过期。
     *
     * @param key 键
     */
    public Boolean persist(String key) {

        if (redisTemplate != null) {
            return redisTemplate.boundValueOps(key).persist();
        }
        return false;
    }

    public Boolean delete(String key) {
        if (redisTemplate != null) {
            return redisTemplate.delete(key);
        }
        return false;
    }

    public Long delete(List<String> keys) {
        if (redisTemplate != null) {
            return redisTemplate.delete(keys);
        }
        return 0L;
    }

    /**
     * 根据key获取值
     *
     * @param key 键 键 值
     */
    public T get(String key) {
        if (redisTemplate != null) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * 将值放入缓存
     *
     * @param key   键   键
     * @param value value 值 true成功 false 失败
     */
    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将值放入缓存并设置时间
     *
     * @param key   键   键
     * @param value value 值
     * @param time  过期时间  时间(秒) -1为无期限 true成功 false 失败
     */
    public void set(String key, T value, long time, TimeUnit timeUnit) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, timeUnit);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 批量添加 key (重复的键会覆盖)
     *
     * @param keyAndValue 键AndValue
     */
    public void batchSet(Map<String, T> keyAndValue) {
        redisTemplate.opsForValue().multiSet(keyAndValue);
    }

    /**
     * 批量添加 key-value 只有在键不存在时,才添加 map 中只要有一个key存在,则全部不添加
     *
     * @param keyAndValue 键AndValue
     */
    public void batchSetIfAbsent(Map<String, T> keyAndValue) {
        redisTemplate.opsForValue().multiSetIfAbsent(keyAndValue);
    }

    /**
     * 对一个 key-value 的值进行加减操作, 如果该 key 不存在 将创建一个key 并赋值该 number 如果 key 存在,但 value
     * 不是长整型 ,将报错
     *
     * @param key    键
     * @param number number
     */
    public Long increment(String key, long number) {
        return redisTemplate.opsForValue().increment(key, number);
    }

    /**
     * 对一个 key-value 的值进行加减操作, 如果该 key 不存在 将创建一个key 并赋值该 number 如果 key 存在,但 value 不是
     * 纯数字 ,将报错
     *
     * @param key    键
     * @param number number
     */
    public Double increment(String key, double number) {
        return redisTemplate.opsForValue().increment(key, number);
    }

    // - - - - - - - - - - - - - - - - - - - - - set类型 - - - - - - - - - - - - - - -
    // - - - - -

    /**
     * 将数据放入set缓存（对象）
     *
     * @param key   键
     * @param value value
     */
    public void sSet(String key, T value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取变量中的值
     *
     * @param key 键 键
     */
    public Set<T> members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取变量中指定个数的元素
     *
     * @param key   键   键
     * @param count 值
     */
    public void randomMembers(String key, long count) {
        redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机获取变量中的元素
     *
     * @param key 键 键
     */
    public Object randomMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 弹出变量中的元素
     *
     * @param key 键 键
     */
    public Object pop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 获取变量中值的长度
     *
     * @param key 键 键
     */
    public Long size(String key) {
        if (redisTemplate != null) {
            return redisTemplate.opsForSet().size(key);
        }
        return null;
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键   键
     * @param value value 值 true 存在 false不存在
     */
    public Boolean sHasKey(String key, T value) {
        if (redisTemplate != null) {
            return redisTemplate.opsForSet().isMember(key, value);
        }
        return false;

    }

    /**
     * 检查给定的元素是否在变量中。
     *
     * @param key 键 键
     * @param obj 元素对象
     */
    public boolean isMember(String key, Object obj) {
        if (redisTemplate != null) {
            return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, obj));
        }
        return false;
    }

    /**
     * 转移变量的元素值到目的变量。
     *
     * @param key     键     键
     * @param value   value   元素对象
     * @param destKey 元素对象
     */
    public Boolean move(String key, T value, String destKey) {
        if (redisTemplate != null) {
            return redisTemplate.opsForSet().move(key, value, destKey);
        }
        return false;
    }

    /**
     * 批量移除set缓存中元素
     *
     * @param key    键    键
     * @param values values 值
     */
    public void remove(String key, Object... values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 通过给定的key求2个set变量的差值
     *
     * @param key     键     键
     * @param destKey 键
     */
    public Set<T> difference(String key, String destKey) {
        return redisTemplate.opsForSet().difference(key, destKey);
    }

    // - - - - - - - - - - - - - - - - - - - - - hash类型 - - - - - - - - - - - - - -
    // - - - - - -

    /**
     * 加入缓存
     *
     * @param key 键 键
     * @param map 键
     */
    public void add(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 加入缓存
     * @param key key
     * @param hashKey hashKey
     * @param value value
     */
    public void add(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 加入缓存
     *
     * @param key 键 键
     * @param map 键
     */
    public void addObject(String key, Map<Integer, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取
     *
     * @param key key
     * @param hashKey hashKey
     */
    public Object getByHashKey(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取 key 下的 所有 hashkey 和 value
     *
     * @param key 键 键
     */
    public Map<Object, T> getHashEntries(String key) {
        return (Map<Object, T>) redisTemplate.opsForHash().entries(key);
    }

    /**
     * 验证指定 key 下 有没有指定的 hashkey
     *
     * @param key     键
     * @param hashKey hashKey
     */
    public boolean hasHashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 获取指定key的值value
     *
     * @param key  键  键
     * @param key2 键2 键
     */
    public Object getMapString(String key, String key2) {
        return redisTemplate.opsForHash().get(key, key2);
    }

    /**
     * 删除指定 hash 的 HashKey
     *
     * @param key      键
     * @param hashKeys 删除成功的 数量
     */
    public Long delete(String key, String... hashKeys) {
        return redisTemplate.opsForHash().delete(key, (Object) hashKeys);
    }

    /**
     * 删除指定 hash 的 HashKey
     *
     * @param key      键
     * @param hashKeys 删除成功的 数量
     */
    public Long delete(String key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 给指定 hash 的 hashkey 做增减操作
     *
     * @param key     键
     * @param hashKey hashKey
     * @param number  number
     */
    public Long increment(String key, String hashKey, long number) {
        return redisTemplate.opsForHash().increment(key, hashKey, number);
    }

    /**
     * 给指定 hash 的 hashkey 做增减操作
     *
     * @param key     键
     * @param hashKey hashKey
     * @param number  number
     */
    public Double increment(String key, String hashKey, Double number) {
        return redisTemplate.opsForHash().increment(key, hashKey, number);
    }

    /**
     * 获取 key 下的 所有 hashkey 字段
     *
     * @param key 键
     */
    public Set<Object> hashKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取指定 hash 下面的 键值对 数量
     *
     * @param key 键
     */
    public Long hashSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    // - - - - - - - - - - - - - - - - - - - - - list类型 - - - - - - - - - - - - - -
    // - - - - - -

    /**
     * 在变量左边添加元素值
     *
     * @param key   键
     * @param value value
     */
    public void leftPush(String key, T value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取集合指定位置的值。
     *
     * @param key   键
     * @param index index
     */
    public Object index(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取指定区间的值。
     *
     * @param key   键
     * @param start start
     * @param end   end
     */
    public List<T> range(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 把最后一个参数值放到指定集合的第一个出现中间参数的前面， 如果中间参数值存在的话。
     *
     * @param key   键
     * @param pivot pivot
     * @param value value
     */
    public void leftPush(String key, T pivot, T value) {
        redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 向左边批量添加参数元素。
     *
     * @param key    键
     * @param values values
     */
    @SafeVarargs
    public final void leftPushAll(String key, T... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 向集合最右边添加元素。
     *
     * @param key   键
     * @param value value
     */
    public void leftPushAll(String key, T value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 向左边批量添加参数元素。
     *
     * @param key    键
     * @param values values
     */
    @SafeVarargs
    public final void rightPushAll(String key, T... values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 以集合方式向右边添加元素。
     *
     * @param key    键
     * @param values values
     */
    public void rightPushAll(String key, Collection<T> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 向已存在的集合中添加元素。
     *
     * @param key   键
     * @param value value
     */
    public Long rightPushIfPresent(String key, T value) {
        if (redisTemplate != null) {
            return redisTemplate.opsForList().rightPushIfPresent(key, value);
        }
        return null;

    }

    /**
     * 向已存在的集合中添加元素。
     *
     * @param key 键
     */
    public Long listLength(String key) {
        if (redisTemplate != null) {
            return redisTemplate.opsForList().size(key);
        }
        return null;
    }

    /**
     * 移除集合中的左边第一个元素。
     *
     * @param key 键
     */
    public void leftPop(String key) {
        redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     *
     * @param key 键
     */
    public void leftPop(String key, long timeout, TimeUnit unit) {
        redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 移除集合中右边的元素。
     *
     * @param key 键
     */
    public void rightPop(String key) {
        redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移除集合中右边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     *
     * @param key 键
     */
    public void rightPop(String key, long timeout, TimeUnit unit) {
        redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    /**
     * 移除集合中右边的元素并返回
     *
     * @param key 键
     */
    public Object rightDropPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 删除某个key
     *
     * @param key 键
     */
    public boolean deleteKey(String key) {
        if (key != null) {
            Boolean status = redisTemplate.delete(key);
            if (status == null) {
                return false;
            }
            return status;
        }
        return false;
    }

    /**
     * 获取集合尺寸
     *
     * @param key 键
     */
    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

}
