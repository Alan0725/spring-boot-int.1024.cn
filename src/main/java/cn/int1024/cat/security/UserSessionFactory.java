package cn.int1024.cat.security;

import cn.int1024.cat.entity.bo.UserSession;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;

/**
 * @Description: 用户会话工厂类
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/18 21:56
 * @Version: 1.0
 */
public class UserSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext sessionContext) {
        if (sessionContext != null) {
            String host = sessionContext.getHost();
            if (host != null) {
                return new UserSession(host);
            }
        }

        return new UserSession();
    }
}
