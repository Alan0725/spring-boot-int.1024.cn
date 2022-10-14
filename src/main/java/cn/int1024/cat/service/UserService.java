package cn.int1024.cat.service;

import cn.int1024.cat.entity.po.User;

import java.util.List;

/**
 * @Description: UserService
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 17:10:00
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 获取所有用户
     * @return List<User>
     */
    List<User> getAll();
}
