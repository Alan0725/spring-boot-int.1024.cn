package cn.int1024.cat.service.impl;

import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.mapper.UserMapper;
import cn.int1024.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 17:11:00
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.queryAll();
    }
}