package cn.int1024.cat.enums;

import lombok.Getter;

/**
 * @Description: 用户性别
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/25 22:45
 * @Version: 1.0
 */
public enum UserGender {
    /**
     * 保密
     */
    SECRECY(0, "保密"),

    /**
     * 女
     */
    WOMAN(1, "女"),

    /**
     * 男
     */
    MAN(2, "男");

    /**
     * 状态码
     */
    @Getter
    final Integer code;

    @Getter
    final String desc;

    UserGender(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
