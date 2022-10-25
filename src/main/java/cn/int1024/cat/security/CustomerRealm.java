package cn.int1024.cat.security;

import cn.int1024.cat.common.util.ApplicationContextUtil;
import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

/**
 * @Description: 自定义Realm
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/24 18:00:00
 * @Version: 1.0
 */
public class CustomerRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String principal = (String) authenticationToken.getPrincipal();
		//获取UserService对象
		UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
		//System.out.println(userService);
		User user = userService.findByUsername(principal);
		if (!ObjectUtils.isEmpty(user)) {
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
		}
		return null;
	}
}

