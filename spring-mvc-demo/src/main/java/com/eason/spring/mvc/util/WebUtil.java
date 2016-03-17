package com.eason.spring.mvc.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

public final class WebUtil {
	public static final int COOKIE_Forever = 60 * 60 * 24 * 365 * 50;
	public static final int COOKIE_Session = -1;

	private final static String CONTEXT_ATTRIBUTE_NAME = "org.springframework.web.servlet.FrameworkServlet.CONTEXT.springMVC";

	/**
	 * 获取spring环境句柄
	 * 
	 * @param request
	 * @return
	 */
	public static ApplicationContext getApplicationContext(ServletRequest request) {
		return getApplicationContext(request.getServletContext());
	}

	public static ApplicationContext getApplicationContext(FilterConfig filterConfig) {
		return getApplicationContext(filterConfig.getServletContext());
	}

	private static ApplicationContext getApplicationContext(ServletContext servletContext) {
		ApplicationContext applicationContext = (ApplicationContext) servletContext
		        .getAttribute(CONTEXT_ATTRIBUTE_NAME);
		if (applicationContext == null)
			throw new IllegalStateException("Not found WebApplicationContext.");
		return applicationContext;
	}

	/**
	 * 取得客户端请求 真实IP
	 */
	public static String getRealIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 如果是本地测试或以上步骤没有取得ip没从请求参数中获取IP
		if (("127.0.0.1".equals(ip) || StringUtils.isEmpty(ip)) && !StringUtils.isEmpty(request.getParameter("ip")))
			ip = request.getParameter("ip");
		return ip;
	}

	public static String getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					String cvalue = cookie.getValue();
					String svalue;
					try {
						svalue = java.net.URLDecoder.decode(cvalue, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}
					if (svalue.length() >= 2) {
						if ((svalue.charAt(0) == '"') && (svalue.charAt(svalue.length() - 1) == '"'))
							svalue = svalue.substring(1, svalue.length() - 1);
					}
					return svalue;
				}
			}
		}
		return null;
	}

	public static void setCookie(String name, String value, boolean httpOnly, HttpServletRequest request,
	        HttpServletResponse response) {
		setCookie(name, value, COOKIE_Session, "/", httpOnly, request, response);
	}

	public static void setCookie(String name, String value, String path, boolean httpOnly, HttpServletRequest request,
	        HttpServletResponse response) {
		setCookie(name, value, COOKIE_Session, path, httpOnly, request, response);
	}

	public static void setCookie(String name, String value, int ageInSecond, String path, boolean httpOnly,
	        HttpServletRequest request, HttpServletResponse response) {
		String host = request.getServerName();
		String domain = null;
		if ("/".equals(path) && (host.charAt(host.length() - 1)) > '9') {
			int tn = host.lastIndexOf('.');
			if (tn >= 0) {
				tn = host.lastIndexOf('.', tn - 1);
				if (tn >= 0) {
					domain = host.substring(tn);
				}
			}
		}
		addCookie(name, value, ageInSecond, domain, path, httpOnly, response);
	}

	public static void setCookie(String name, String value, int ageInSecond, String domain, String path,
	        HttpServletResponse response) {
		addCookie(name, value, ageInSecond, domain, path, false, response);
	}

	public static void setCookie(String name, String value, String domain, String path, HttpServletResponse response) {
		addCookie(name, value, COOKIE_Session, domain, path, false, response);
	}

	public static void removeCookie(String name, String domain, String path, HttpServletResponse response) {
		addCookie(name, null, 0, domain, path, false, response);
	}

	private static void addCookie(String name, String value, int maxAge, String domain, String path, boolean httpOnly,
	        HttpServletResponse response) {
		if (value != null) { // 必须进行urlencode, 否则特殊字符值会导致浏览器端无法正常解析
			try {
				value = java.net.URLEncoder.encode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		if (domain != null)
			cookie.setDomain(domain);
		if (path != null)
			cookie.setPath(path);
		if (httpOnly)
			cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}
}
