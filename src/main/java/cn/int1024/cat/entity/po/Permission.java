package cn.int1024.cat.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 权限
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/18 22:20
 * @Version: 1.0
 */
@Data
public class Permission implements Serializable {

    private Integer id;

    private String name;

    private String value;
}
