package cn.int1024.cat.controller;

import cn.int1024.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: IndexController
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 11:54:00
 * @Version: 1.0
 */
@Controller
public class IndexController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
