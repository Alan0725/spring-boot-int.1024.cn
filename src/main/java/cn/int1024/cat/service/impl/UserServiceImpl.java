package cn.int1024.cat.service.impl;

import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.entity.vo.UserInfo;
import cn.int1024.cat.mapper.UserMapper;
import cn.int1024.cat.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Integer addUser(User user) {
        return userMapper.addUser(user) > 0 ? user.getId() : 0;
    }

    @Override
    public Integer delAll() {
        return userMapper.delAll();
    }

    @Override
    public UserInfo getUserInfo(String username) {
        return userMapper.getUserInfo(username);
    }

    @Override
    public List<UserInfo> getAll() {
        PageHelper.startPage(1, 10);
        return userMapper.queryAll();
    }
}
