package cn.int1024.cat.controller;

import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.service.UserService;
import cn.int1024.cat.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
    public List<User> getAllUser() {
        return userService.getAll();
    }

    @ResponseBody
    @RequestMapping("delAll")
    public Integer delAll() {
        return userService.delAll();
    }
    @ResponseBody
    @RequestMapping("add")
    public Integer add() {
        User user = new User();
        user.setAccount("DengXiangHong");
        user.setBirthday(new Date());
        user.setAvatar("404");
        user.setGender(1);
        user.setStates(0);
        user.setPhoneNumber("182****7021");
        user.setEmail("int1024@int.com");
        user.setNickName("双料特工·钏钐钾");
        return userService.addUser(user);
    }
}
