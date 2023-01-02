package cn.int1024.cat.config;

import cn.int1024.cat.entity.bo.UserSession;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @Description: RedisConfig
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 11:54:00
 * @Version: 1.0
 */
@EnableCaching
@Configuration
public class RedisConfigurer {
    /**
     * 实例化 RedisTemplate 对象
     */
    @Bean
    public RedisTemplate<String, UserSession> sessionRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, UserSession> redisTemplate = new RedisTemplate<>();
        // key 都使用 String 序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserSession.class));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(UserSession.class));
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

}
