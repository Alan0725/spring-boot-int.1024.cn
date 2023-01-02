package cn.int1024.cat;

import cn.int1024.cat.entity.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/12 23:41
 * @Version: 1.0
 */
@Slf4j
@SpringBootTest
public class RedisServerTests {

    @Resource
    RedisTemplate<String, User> redisTemplate;

    @Test
    public void put() {
        User user = new User();
        user.setUsername("213123");
        redisTemplate.opsForHash().put("ABC", "A", user);
    }

    @Test
    public void get() {
        log.debug("{}", ((User) redisTemplate.opsForHash().get("ABC", "A")).getUsername());
    }
}
