package cn.int1024.cat.service;

import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.entity.vo.UserInfo;

import java.util.List;

/**
 * @Description: UserService
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 17:10:00
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param userId    用户ID
     * @return  用户信息
     */
    UserInfo getUserInfo(int userId);

    /**
     * 获取所有用户
     *
     * @return List<User>
     */
    List<User> getAll();

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 用户ID
     */
    Integer addUser(User user);

    /**
     * 删除所有
     * @return  删除数量
     */
    Integer delAll();
}
