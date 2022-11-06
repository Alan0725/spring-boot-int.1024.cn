package cn.int1024.cat.mapper;

import cn.int1024.cat.entity.po.User;
import cn.int1024.cat.entity.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: UserMapper
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 17:04:00
 * @Version: 1.0
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 用户ID
     */
    Integer save(User user);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo findUserInfoByUsername(String username);

    /**
     * @param username
     * @return
     */
    User findByUsername(String username);
}
