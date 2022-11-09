package cn.int1024.cat.security;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @Description: 会话管理
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/9 22:20
 * @Version: 1.0
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 获取请求头Authorization中的数据
        String id = WebUtils.toHttp(request).getHeader("Authorization");
        if(!StringUtils.hasLength(id)) {
            // 生成新的 sessionId
            return super.getSessionId(request, response);
        }else{
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
    }
}
