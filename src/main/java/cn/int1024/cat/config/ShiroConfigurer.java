package cn.int1024.cat.config;

import cn.int1024.cat.security.CustomerRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/24 18:01:00
 * @Version: 1.0
 */
@Configuration
public class ShiroConfigurer {

	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 给ShiroFilter配置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 配置系统受限资源
		Map<String, String> map = new HashMap<>(1);
		map.put("/user/login","anon");
		map.put("/**","authc");
		// 设置认证界面路径
		shiroFilterFactoryBean.setLoginUrl("/login.jsp");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	@Bean
	public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		return securityManager;
	}

	@Bean
	public Realm getRealm() {
		return new CustomerRealm();
	}
}
