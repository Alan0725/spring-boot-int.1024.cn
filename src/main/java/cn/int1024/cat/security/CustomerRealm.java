package cn.int1024.cat.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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
		if ("admin".equals(principal)) {
			return new SimpleAuthenticationInfo(principal, "111111", this.getName());
		}
		return null;
	}
}

