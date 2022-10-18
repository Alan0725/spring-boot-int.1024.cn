package cn.int1024.cat.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: User
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 11:54:00
 * @Version: 1.0
 */
@Data
public class User {

    Integer id;

    String account;

    String password;

    String nickName;

    Integer gender;

    String phoneNumber;

    String email;

    String avatar;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    Date birthday;

    Integer states;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    Date registerTime;
}
