package cn.int1024.cat.cache;

import cn.int1024.cat.common.redis.RedisService;
import cn.int1024.cat.entity.bo.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 会话缓存
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/10 22:25
 * @Version: 1.0
 */
@Slf4j
@Component
public class SessionCache extends AbstractSessionDAO {

    private static final String CACHE_NAME = "SessionCache";

    private RedisService<Session> redisService;

    @Autowired
    public void setRedisService(RedisService<Session> redisService) {
        this.redisService = redisService;
    }

    @Override
    protected Serializable doCreate(Session session) {
        log.debug("SessionCache create {}", session);
        Serializable sessionId = this.generateSessionId(session);
        ((UserSession) session).setId(sessionId);
        this.storeSession(sessionId, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        log.debug("SessionCache doReadSession {}", serializable);
        Object object = redisService.getByHashKey(CACHE_NAME, serializable);
        return Objects.isNull(object) ? null : (Session) object;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.debug("SessionCache update {}", session);
        this.storeSession(session.getId(), session);
    }

    @Override
    public void delete(Session session) {
        log.debug("SessionCache delete {}", session);
        redisService.delete(CACHE_NAME, session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.debug("SessionCache getActiveSessions");
        Map<Object, Session> map = redisService.getHashEntries(CACHE_NAME);
        if (Objects.isNull(map) || map.isEmpty()) {
            return null;
        }
        return map.values();
    }

    private void storeSession(Serializable serializable, Session session) {
        redisService.add(CACHE_NAME, serializable, session);
    }
}
