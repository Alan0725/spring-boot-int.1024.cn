package cn.int1024.cat.entity.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.StoppedSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.*;

/**
 * @Description: 用户会话
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/16 22:26
 * @Version: 1.0
 */
@Data
@Slf4j
public class UserSession implements ValidatingSession, Serializable {
    /**
     * ID
     */
    private Serializable id;
    /**
     * 开始时间
     */
    private Date startTimestamp;
    /**
     * 停止时间
     */
    private Date stopTimestamp;
    /**
     * 最后一次访问时间
     */
    private Date lastAccessTime;
    /**
     * 超时时间
     */
    private long timeout;
    /**
     * 是否过期
     */
    private boolean expired;
    /**
     * host
     */
    private String host;
    /**
     * 属性
     */
    private Map<Object, Object> attributes;

    /**
     * 是否有效
     */
    private boolean valid;

    public UserSession() {
        this.timeout = 1800000L;
        this.startTimestamp = new Date();
        this.lastAccessTime = this.startTimestamp;
    }

    public UserSession(String host) {
        this();
        this.host = host;
    }

    protected void expire() {
        this.stop();
        this.expired = true;
    }

    protected boolean isStopped() {
        return this.getStopTimestamp() != null;
    }

    protected boolean isTimedOut() {
        if (this.isExpired()) {
            return true;
        } else {
            long timeout = this.getTimeout();
            if (timeout >= 0L) {
                Date lastAccessTime = this.getLastAccessTime();
                if (lastAccessTime == null) {
                    String msg = "session.lastAccessTime for session with id [" + this.getId() + "] is null.  This value must be set at least once, preferably at least upon instantiation.  Please check the " + this.getClass().getName() + " implementation and ensure this value will be set (perhaps in the constructor?)";
                    throw new IllegalStateException(msg);
                } else {
                    long expireTimeMillis = System.currentTimeMillis() - timeout;
                    Date expireTime = new Date(expireTimeMillis);
                    return lastAccessTime.before(expireTime);
                }
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("No timeout for session with id [" + this.getId() + "].  Session is not considered expired.");
                }

                return false;
            }
        }
    }

    @Override
    public boolean isValid() {
        return !this.isStopped() && !this.isExpired();
    }

    @Override
    public void validate() throws InvalidSessionException {
        if (this.isStopped()) {
            String msg = "Session with id [" + this.getId() + "] has been explicitly stopped.  No further interaction under this session is allowed.";
            throw new StoppedSessionException(msg);
        } else if (this.isTimedOut()) {
            this.expire();
            Date lastAccessTime = this.getLastAccessTime();
            long timeout = this.getTimeout();
            Serializable sessionId = this.getId();
            DateFormat df = DateFormat.getInstance();
            String msg = "Session with id [" + sessionId + "] has expired. Last access time: " + df.format(lastAccessTime) + ".  Current time: " + df.format(new Date()) + ".  Session timeout is set to " + timeout / 1000L + " seconds (" + timeout / 60000L + " minutes)";
            if (log.isTraceEnabled()) {
                log.trace(msg);
            }
            throw new ExpiredSessionException(msg);
        }
    }

    @Override
    public void touch() throws InvalidSessionException {
        this.lastAccessTime = new Date();
    }

    @Override
    public void stop() throws InvalidSessionException {
        if (this.stopTimestamp == null) {
            this.stopTimestamp = new Date();
        }
    }

    @Override
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        Map<Object, Object> attributes = this.getAttributes();
        return attributes == null ? Collections.emptySet() : attributes.keySet();
    }
    @Override
    public Object getAttribute(Object key) throws InvalidSessionException {
        Map<Object, Object> attributes = this.getAttributes();
        return attributes == null ? null : attributes.get(key);
    }

    @Override
    public void setAttribute(Object key, Object value) throws InvalidSessionException {
        if (value == null) {
            this.removeAttribute(key);
        } else {
            this.getAttributesLazy().put(key, value);
        }
    }

    @Override
    public Object removeAttribute(Object key) throws InvalidSessionException {
        Map<Object, Object> attributes = this.getAttributes();
        return attributes == null ? null : attributes.remove(key);
    }

    private Map<Object, Object> getAttributesLazy() {
        Map<Object, Object> attributes = this.getAttributes();
        if (attributes == null) {
            attributes = new HashMap<>();
            this.setAttributes(attributes);
        }
        return attributes;
    }
}
