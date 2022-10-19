package cn.int1024.cat.security.intercepors;

import cn.int1024.cat.common.util.RequestUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 15:53:00
 * @Version: 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		// 允许跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "3600");

		// 放行静态资源
		if (handler instanceof ResourceHttpRequestHandler) {
			return true;
		}
		// 获取token
		String token = request.getHeader(RequestUtil.HEADER_TOKEN_NAME);
		if (StringUtils.isEmpty(token)) {
			this.setResult(response, "No Permission");
		} else {
			try {
//				JwtParser parser = Jwts.parser();
//				parser.setSigningKey("miyao");
//				Jws<Claims> ClaimsJws =parser.parseClaimsJws(token);
				return true;
			} catch (Exception e) {
				this.setResult(response, e.getMessage());
			}
		}
		return false;
	}

	private void setResult(HttpServletResponse response, String message) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		JSONObject res = new JSONObject();
		res.put("code", 403);
		res.put("message", message);
		PrintWriter out = response.getWriter();
		out.write(res.toString());
		out.flush();
		out.close();
	}
}
