package cn.int1024.cat.common.util;

import cn.int1024.cat.enums.App;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 请求相关工具
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 15:57:00
 * @Version: 1.0
 */
public class RequestUtil {
	/**
	 * token
	 */
	public final static String HEADER_TOKEN_NAME = "X-Token";

	/**
	 * 应用ID
	 */
	public final static String HEADER_APP_ID = "X-App-Id";

	/**
	 * 是否为管理端
	 */
	public static boolean isAdminClient(HttpServletRequest request) {
		String appId = request.getHeader(HEADER_APP_ID);
		return !StringUtils.isEmpty(appId) && Integer.parseInt(appId) == App.ADMIN_CLIENT.getId();
	}
}
