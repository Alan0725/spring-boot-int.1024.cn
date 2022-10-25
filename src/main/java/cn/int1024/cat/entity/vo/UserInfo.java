package cn.int1024.cat.entity.vo;

import cn.int1024.cat.entity.po.Permission;
import cn.int1024.cat.entity.po.Role;
import cn.int1024.cat.entity.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: 双料特工 · 钏疝钾
 * @Date: 2022/10/18 22:22
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends User {

    Role role;

    List<Permission> permissions;
}
