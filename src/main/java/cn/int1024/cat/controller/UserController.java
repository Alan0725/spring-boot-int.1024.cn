package cn.int1024.cat.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/25 18:02:00
 * @Version: 1.0
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

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
			log.debug("登录成功！！！");
			return "redirect:/index.jsp";
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			log.debug("用户错误！！！");
		} catch (IncorrectCredentialsException e) {
			log.debug("密码错误！！！");
		}
		return "redirect:/login.jsp";
	}
}
