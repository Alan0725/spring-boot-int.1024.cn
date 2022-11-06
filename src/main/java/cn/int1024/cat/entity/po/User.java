package cn.int1024.cat.entity.po;

import cn.int1024.cat.enums.UserGender;
import cn.int1024.cat.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: User
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/14 11:54:00
 * @Version: 1.0
 */
@Data
public class User implements Serializable {

    Integer id;

    String username;

    String password;

    String nickName;

    /**
     * @see UserGender
     */
    Integer gender;

    String phoneNumber;

    String email;

    String avatar;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    Date birthday;

    /**
     * @see UserStatus
     */
    Integer status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    Date registerTime;

    /**
     * 是否启用
     *
     * @return boolean
     */
    public boolean isDisabled() {
        return this.status.equals(UserStatus.DISABLE.getStatus());
    }
}
