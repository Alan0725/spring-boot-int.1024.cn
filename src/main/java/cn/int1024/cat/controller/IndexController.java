package cn.int1024.cat.controller;

import cn.int1024.cat.entity.vo.UserInfo;
import cn.int1024.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @ResponseBody
    @RequestMapping("all")
    @PreAuthorize("hasAuthority('user222')")
    public List<UserInfo> getAllUser() {
        return userService.getAll();
    }
}
