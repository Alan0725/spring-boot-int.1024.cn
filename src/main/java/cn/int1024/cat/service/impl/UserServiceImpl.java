package cn.int1024.cat.service.impl;

import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.entity.vo.UserInfo;
import cn.int1024.cat.enums.UserGender;
import cn.int1024.cat.enums.UserStatus;
import cn.int1024.cat.mapper.UserMapper;
import cn.int1024.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description: UserServiceImpl
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 17:11:00
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) {
        return userMapper.findUserInfoByUsername(username);
    }

    @Override
    public Integer register(User user) {
        user.setStatus(UserStatus.NORMAL.getStatus());
        user.setGender(UserGender.SECRECY.getCode());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userMapper.save(user);
    }

}
