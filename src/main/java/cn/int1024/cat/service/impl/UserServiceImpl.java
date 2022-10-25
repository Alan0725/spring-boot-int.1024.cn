package cn.int1024.cat.service.impl;

import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.entity.vo.UserInfo;
import cn.int1024.cat.enums.UserGender;
import cn.int1024.cat.enums.UserStatus;
import cn.int1024.cat.mapper.UserMapper;
import cn.int1024.cat.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 17:11:00
 * @Version: 1.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public UserInfo getUserInfo(String username) {
        return userMapper.getUserInfo(username);
    }

    @Override
    public Integer register(User user) {
        user.setStatus(UserStatus.NORMAL.getStatus());
        user.setGender(UserGender.SECRECY.getCode());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userMapper.save(user);
    }

}
