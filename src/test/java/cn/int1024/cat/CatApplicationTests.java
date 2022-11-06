package cn.int1024.cat;

import cn.int1024.cat.entity.vo.UserInfo;
import cn.int1024.cat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest
class CatApplicationTests {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    void contextLoads() {
        UserInfo userInfo = userService.getUserInfo("admin");
        System.out.println(userInfo.toString());
    }
}
