package cn.int1024.cat.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.SessionDAO;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 会话缓存
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/10 22:25
 * @Version: 1.0
 */
@Slf4j
public class SessionCache implements SessionDAO {
    @Override
    public Serializable create(Session session) {
        log.debug("SessionCache create {}", session);
        return null;
    }

    @Override
    public Session readSession(Serializable serializable) throws UnknownSessionException {
        log.debug("SessionCache readSession {}", serializable.toString());
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.debug("SessionCache update {}", session);

    }

    @Override
    public void delete(Session session) {
        log.debug("SessionCache delete {}", session);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.debug("SessionCache getActiveSessions");
        return null;
    }
}
