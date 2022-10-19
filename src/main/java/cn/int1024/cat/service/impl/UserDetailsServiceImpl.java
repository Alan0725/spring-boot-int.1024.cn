package cn.int1024.cat.service.impl;

import cn.int1024.cat.entity.po.Permission;
import cn.int1024.cat.entity.vo.UserInfo;
import cn.int1024.cat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 10:14:00
 * @Version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = mapper.getUserInfo(username);
        if (Objects.isNull(userInfo)) {
            throw new BadCredentialsException("账号不存在");
        }
        // 权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Permission permission : userInfo.getPermissions()) {
            String permissionValue = permission.getValue();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }
        return User.builder()
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .disabled(userInfo.isDisabled())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .authorities(authorities)
                .build();
    }
}
