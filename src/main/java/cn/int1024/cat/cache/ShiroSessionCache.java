package cn.int1024.cat.cache;

import cn.int1024.cat.common.util.ApplicationContextUtil;
import cn.int1024.cat.entity.bo.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.util.ArrayList;
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
public class ShiroSessionCache extends AbstractSessionDAO {

    private static final String CACHE_NAME = "CatSessionCache";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        ((UserSession) session).setId(sessionId);
        this.storeSession(sessionId, session);
        return sessionId;
    }

    @Override
    protected UserSession doReadSession(Serializable serializable) {
        Object object = getRedisTemplate().opsForHash().get(CACHE_NAME, serializable);
        return Objects.isNull(object) ? null : (UserSession) object;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.storeSession(session.getId(), session);
    }

    @Override
    public void delete(Session session) {
        getRedisTemplate().opsForHash().delete(CACHE_NAME, session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.debug("SessionCache getActiveSessions");
        Map<Object, Object> map = getRedisTemplate().opsForHash().entries(CACHE_NAME);
        if (map.isEmpty()) {
            return null;
        }
        Collection<Session> sessions = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            UserSession session = (UserSession) entry.getValue();
            if (session.isValid()) {
                sessions.add(session);
            }
        }
        return sessions;
    }

    private void storeSession(Serializable serializable, Session session) {
        getRedisTemplate().opsForHash().put(CACHE_NAME, serializable, session);
    }


    private RedisTemplate<Serializable, Session> getRedisTemplate() {
        RedisTemplate<Serializable, Session> redisTemplate = (RedisTemplate<Serializable, Session>) ApplicationContextUtil.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
