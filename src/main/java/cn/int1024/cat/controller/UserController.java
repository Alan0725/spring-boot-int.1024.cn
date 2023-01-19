package cn.int1024.cat.controller;

import cn.int1024.cat.common.util.Result;
import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.enums.ResultCode;
import cn.int1024.cat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @Description: UserController
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/25 18:02:00
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/info")
	public Result<User> info(String token) {
		// TOKEN 校验
		Subject subject = SecurityUtils.getSubject();
		log.debug("Token：{}，SessionId：{}，Principal：{}", token, subject.getSession().getId(), subject.getPrincipal());
		return Result.success(userService.findByUsername((String) subject.getPrincipal()));
	}

	@PostMapping("logout")
	public Result<String> logout() {
		SecurityUtils.getSubject().logout();
		return Result.success("退出登录成功");
	}

	@PostMapping("/login")
	public Result<String> login(String username, String password) {
		if(Objects.isNull(username) || Objects.isNull(password) ) {
			return Result.error(ResultCode.USERNAME_PASSWORD_INCORRECT.getCode(), "账号或密码错误");
		}
		//获取主题对象
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(new UsernamePasswordToken(username, password));
			String principal = (String) subject.getPrincipal();
			log.debug("{} 登录成功！！！", principal);
			return Result.success("登录成功");
		} catch (UnknownAccountException | IncorrectCredentialsException e) {
			return Result.error("账号或密码错误");
		}
	}

}
