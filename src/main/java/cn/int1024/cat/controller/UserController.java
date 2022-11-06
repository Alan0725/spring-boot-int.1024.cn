package cn.int1024.cat.controller;

import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Description: UserController
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/25 18:02:00
 * @Version: 1.0
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/register")
	public String register(User user) {
		try {
			userService.register(user);
			return "redirect:/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/register.jsp";
		}
	}

	@RequestMapping("logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login.jsp";
	}

	@RequestMapping("/login")
	public String login(String username, String password) {
		//获取主题对象
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(new UsernamePasswordToken(username,password));
			String principal = (String) subject.getPrincipal();
			log.debug("{} 登录成功！！！", principal);
			return "redirect:/index.jsp";
		} catch (UnknownAccountException e) {
			log.debug("用户错误！！！");
		} catch (IncorrectCredentialsException e) {
			log.debug("密码错误！！！");
		}
		return "redirect:/login.jsp";
	}
}
