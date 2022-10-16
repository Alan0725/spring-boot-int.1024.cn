package cn.int1024.cat.service.impl;

import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @Description: 用户认证策略
 * @Author: 双料特工 · 钏疝钾
 * @Date: 2022/10/16 0:07
 * @Version: 1.0
 */
@Slf4j
@Service
public class CatUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest request;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.selectUser(username);
//        //logger.info(user.getName());
//        if (user == null){
//            throw new UsernameNotFoundException("用户名不存在!");
//        }
//        HttpSession session = request.getSession();
//        session.setAttribute("user",user);
//        session.setAttribute("sessusername",username);
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        //角色
//        authorities.add(new SimpleGrantedAuthority(user.getRole()));
//
//        //权限（为了测试，硬编码，实际上应该从数据库中读取）
//        authorities.add(new SimpleGrantedAuthority("1"));
//
//        log.info(user.getName()+"角色权限为："+authorities.toString());

//        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),authorities);
        return null;
    }
}
