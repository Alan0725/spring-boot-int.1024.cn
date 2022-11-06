package cn.int1024.cat.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description: 自定义认证加密方式
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/25 22:32
 * @Version: 1.0
 */
@Slf4j
public class CustomerCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        // @link: <a href="https://blog.csdn.net/superxmh/article/details/118497296">...</a>
        return new BCryptPasswordEncoder().matches(new String(token.getPassword()).trim(), info.getCredentials().toString());
    }
}
