package cn.int1024.cat.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 角色
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/18 22:37
 * @Version: 1.0
 */
@Data
public class Role implements Serializable {

    Integer id;

    String name;

    String value;

    Integer level;
}
