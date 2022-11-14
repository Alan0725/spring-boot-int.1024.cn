package cn.int1024.cat;

import cn.int1024.cat.common.redis.RedisService;
import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.entity.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/12 23:41
 * @Version: 1.0
 */
@Slf4j
@SpringBootTest
public class RedisServerTests {

    RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Test
    public void put() {
        User user = new User();
        user.setStatus(1);
        redisService.set("AAA", user);
        redisService.set("A", "AAA");
    }

    @Test
    public void get() {
        log.info("get(A) {}", (String) redisService.get("A"));
    }
}
