package com.dxh.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 首页
 * @Author: 双料特工 · 钏疝钾
 * @Date: 2022/10/12 21:19
 * @Version: 1.0
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
