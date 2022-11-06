package cn.int1024.cat.security;

import cn.int1024.cat.entity.po.Permission;
import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.entity.vo.UserInfo;
import cn.int1024.cat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Description: 自定义Realm
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/24 18:00:00
 * @Version: 1.0
 */
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        //基于角色授权
        UserInfo userInfo = userService.findUserInfoByUsername(username);
        if (!CollectionUtils.isEmpty(userInfo.getRoles())) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            userInfo.getRoles().forEach(role -> {
                info.addRole(role.getValue());
                List<Permission> permissions = role.getPermissions();
                if(!CollectionUtils.isEmpty(permissions)){
                    permissions.forEach(perm->{
                        info.addStringPermission(perm.getValue());
                    });
                }
            });
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.findByUsername(username);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
        }
        return null;
    }
}

