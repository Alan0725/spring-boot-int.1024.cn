package cn.int1024.cat.security.filter;

import cn.int1024.cat.common.util.RequestUtil;
import cn.int1024.cat.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 登录拦截器
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 15:53:00
 * @Version: 1.0
 */
@Slf4j
public class ResultFormAuthenticationFilter extends FormAuthenticationFilter {

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			} else {
				return true;
			}
		} else {
			if(RequestUtil.isClient((HttpServletRequest) request)){
				saveRequest(request);
				writeResult(response);
				return false;
			}
			return false;
		}
	}

	private void writeResult(ServletResponse servletResponse){
		if(servletResponse instanceof HttpServletResponse){
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			try {
				response.setHeader("Content-Type", "application/json;charset=UTF-8");
				response.getWriter().write(Result.noPermission("未登录").toJSON().toJSONString());
				response.getWriter().flush();
			} catch (IOException e) {
				log.error("", e);
			}
		}
	}
}
