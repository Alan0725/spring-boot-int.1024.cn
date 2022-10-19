package cn.int1024.cat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 11:19:00
 * @Version: 1.0
 */
public class BcryptTests {

    @Test
    public void testEncode() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        for (int i = 0; i < 10; i++) {
            String encodedPassword = passwordEncoder.encode(rawPassword);
            System.out.println("原密码=" + rawPassword + "，密文=" + encodedPassword);
        }
    }

    @Test
    public void testMatch() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String[] encodedPasswords = {
                "$2a$10$fx73BdgI5WzMvgYqilkkW.GgsbTvaJodWHmmh21FzAS6NhS8.NjV6",
                "$2a$10$QcIMO2.xloiNas2MOIhzZu81itsgpsWe9Tg4WCZwmZ.lRT.VHN/h.",
                "$2a$10$pLBz88IBNxX9e.I4hNYx9enfhzaVSX4YLi6lP.ajTl31k2lMfJbZa"
        };
        for (int i = 0; i < encodedPasswords.length; i++) {
            boolean result = passwordEncoder.matches(rawPassword, encodedPasswords[i]);
            System.out.println("原密码=" + rawPassword + "，密文=" + encodedPasswords[i] + "，验证结果=" + result);
        }
    }
}
