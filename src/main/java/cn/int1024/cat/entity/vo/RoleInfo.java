package cn.int1024.cat.entity.vo;

import cn.int1024.cat.entity.po.Permission;
import cn.int1024.cat.entity.po.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 角色信息
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/5 9:54
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleInfo extends Role implements Serializable {
    List<Permission> permissions;
}
