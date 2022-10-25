package cn.int1024.cat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import cn.int1024.cat.security.intercepors.LoginInterceptor;
/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 14:38:00
 * @Version: 1.0
 */
@SpringBootConfiguration
public class WebConfigurer implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		String[] excludePath = {
//				"/register",
//				"/login",
//				"/*.js",
//				"/*.css"
//		};
//		registry.addInterceptor(loginInterceptor)
//				//所有路径都被拦截
//				.addPathPatterns("/**")
//				//添加不拦截的路径
//				.excludePathPatterns(excludePath);
	}
}
