package cn.int1024.cat.entity.bo;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 用户会话
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/16 22:26
 * @Version: 1.0
 */
@Getter
@Setter
public class UserSession extends SimpleSession {
    private Serializable id;
    private Date startTimestamp;
    private Date stopTimestamp;
    private Date lastAccessTime;
    private long timeout;
    private boolean expired;
    private String host;
    private Map<Object, Object> attributes;

    public UserSession() {
        this.timeout = 1800000L;
        this.startTimestamp = new Date();
        this.lastAccessTime = this.startTimestamp;
    }

    public UserSession(String host) {
        this();
        this.host = host;
    }

}
