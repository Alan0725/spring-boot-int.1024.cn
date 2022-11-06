package cn.int1024.cat.enums;

import lombok.Getter;

/**
 * @Description: APP
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 21:54
 * @Version: 1.0
 */
public enum App {
    /**
     * 管理端
     */
    ADMIN_CLIENT(1, "AdminClient");

    @Getter
    private final int id;

    @Getter
    private final String name;

    App(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
