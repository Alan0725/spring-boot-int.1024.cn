package cn.int1024.cat.security;

import cn.int1024.cat.common.util.RequestUtil;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @Description: 会话管理
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/11/9 22:20
 * @Version: 1.0
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String id;
        // 客户端从请求头获取
        if(RequestUtil.isClient(httpServletRequest)) {
            id = RequestUtil.getHeaderJsessionid(httpServletRequest);
            if(StringUtils.hasLength(id)) {
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
                request.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, this.isSessionIdUrlRewritingEnabled());
                return id;
            }
        }
        return super.getSessionId(request, response);
    }
}
