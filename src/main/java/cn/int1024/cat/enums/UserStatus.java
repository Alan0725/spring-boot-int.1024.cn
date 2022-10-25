package cn.int1024.cat.enums;

import lombok.Getter;

/**
 * @Description: 用户状态
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 10:29:00
 * @Version: 1.0
 */
public enum UserStatus {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 禁用
     */
    DISABLE(0, "禁用");

    /**
     * 状态码
     */
    @Getter
    final Integer status;

    /**
     * 状态描述
     */
    @Getter
    final String desc;

    UserStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
