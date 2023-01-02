package cn.int1024.cat.enums;

import lombok.Getter;

/**
 * @Description: 状态码
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 21:09
 * @Version: 1.0
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(20000, "success"),
    /**
     * 发生错误
     */
    ERROR(50000, "error"),
    /**
     * 无效访问令牌
     */
    INVALID_ACCESS_TOKEN(50001, "invalid access token"),

    /**
     * 已在其它地方登录
     */
    ALREADY_LOGIN(50002, "already login in other place"),

    /**
     * 访问令牌已过期
     */
    ACCESS_TOKEN_EXPIRED(50003, "access token expired"),
    /**
     * 无效用户，用户不存在
     */
    INVALID_USER(50004, "invalid user (user not exist)"),
    /**
     * 用户或密码不正确
     */
    USERNAME_PASSWORD_INCORRECT(50005, "username or password is incorrect"),
    /**
     * 无权限
     */
    NO_PERMISSION(50016, "no permission");

    /**
     * 状态码
     */
    @Getter
    private final int code;

    /**
     * 信息
     */
    @Getter
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
