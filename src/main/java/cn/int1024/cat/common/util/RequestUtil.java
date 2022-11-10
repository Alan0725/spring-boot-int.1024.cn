package cn.int1024.cat.common.util;

import cn.int1024.cat.enums.App;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 请求相关工具
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 15:57:00
 * @Version: 1.0
 */
public class RequestUtil {
    /**
     * 认证token
     */
    public final static String HEADER_AUTH_TOKEN = "C-AT";

    /**
     * 应用ID
     */
    public final static String HEADER_APP_ID = "C-AI";

    /**
     * 会话ID
     */
    public final static String HEADER_JSESSIONID = "C-SI";

    /**
     * 是否为管理端
     */
    public static boolean isAdminClient(HttpServletRequest request) {
        String appId = request.getHeader(HEADER_APP_ID);
        return StringUtils.hasLength(appId) && Integer.parseInt(appId) == App.ADMIN_CLIENT.getId();
    }

    /**
     * 是否为客户端
     */
    public static boolean isClient(HttpServletRequest request) {
        return StringUtils.hasLength(request.getHeader(HEADER_APP_ID));
    }

    /**
     * 从头部获取AppId
     */
    public static int getHeaderAppId(HttpServletRequest request) {
        return StringUtils.hasLength(request.getHeader(HEADER_APP_ID)) ? Integer.parseInt(request.getHeader(HEADER_APP_ID)) : 0;
    }

    /**
     * 从头部获取会话 id
     */
    public static String getHeaderJsessionid(HttpServletRequest request) {
        return request.getHeader(HEADER_JSESSIONID);
    }

    /**
     * 从头部获取认证令牌
     */
    public static String getHeaderAuthToken(HttpServletRequest request) {
        return request.getHeader(HEADER_AUTH_TOKEN);
    }
}
