package cn.int1024.cat.controller;

import cn.int1024.cat.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: LoginController
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 16:14:00
 * @Version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @ResponseBody
    @GetMapping("/login")
    public Result<String> login(String username, String password) {
        log.debug("UserName: {}, password: {}", username, password);
        return Result.success("111");
    }
}
