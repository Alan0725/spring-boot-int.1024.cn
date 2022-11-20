package cn.int1024.cat.config;

import cn.int1024.cat.cache.SessionCache;
import cn.int1024.cat.security.*;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: ShiroConfigurer
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/24 18:01:00
 * @Version: 1.0
 */
@Configuration
public class ShiroConfigurer {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给ShiroFilter配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置系统受限资源
        Map<String, String> map = new HashMap<>(1);
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        map.put("/register.jsp", "anon");
        map.put("/**", "authc");
        // 设置认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        // 设置自定义认证加密方式
        customerRealm.setCredentialsMatcher(new CustomerCredentialsMatcher());
        //开启缓存管理器
        customerRealm.setCacheManager(new ShiroRedisCacheManager());
        customerRealm.setCachingEnabled(true);
        customerRealm.setAuthenticationCachingEnabled(true);
        customerRealm.setAuthenticationCacheName("authentication");
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthorizationCacheName("authorization");
        return customerRealm;
    }

    @Bean
    public SessionDAO sessionDAO() {
        return new SessionCache();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new UserSessionFactory();
    }

    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setSessionFactory(sessionFactory());
        return sessionManager;
    }

}
