package cn.int1024.cat.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: 双料特工 · 钏疝钾
 * @Date: 2022/10/18 22:18
 * @Version: 1.0
 */
@Component
public class GlobalPasswordEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 执行加密
     *
     * @param rawPassword 明文密码（原文）
     * @return 密文密码（加密后的结果）
     */
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
