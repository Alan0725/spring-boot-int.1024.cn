package cn.int1024.cat.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: 双料特工 · 钏疝钾
 * @Date: 2022/10/19 21:09
 * @Version: 1.0
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200, "success"),
    /**
     * 发生错误
     */
    ERROR(500, "error"),
    /**
     * 无权限
     */
    NO_PERMISSION(403, "no permission");

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
