package com.eason.spring.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.eason.spring.mvc.util.RedisHttpSession;
import com.eason.spring.mvc.util.WebUtil;

/**
 * 基于Redis的Session共享. 仅支持String类型的值 参考
 * http://blog.itpub.net/29254281/viewspace-1063018/
 */
public class RedisSessionFilter implements Filter {
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	        ServletException {
		ApplicationContext applicationContext = WebUtil.getApplicationContext(filterConfig);
		StringRedisTemplate redisTemplate = applicationContext.getBean("redisTemplate", StringRedisTemplate.class);
		RedisHttpSession session = new RedisHttpSession((HttpServletRequest) request, (HttpServletResponse) response,
		        redisTemplate);
		RedisHttpSessionRequest requestWrapper = new RedisHttpSessionRequest((HttpServletRequest) request, session);
		chain.doFilter(requestWrapper, response);
	}

	@Override
	public void destroy() {
	}

	static class RedisHttpSessionRequest extends HttpServletRequestWrapper {
		private HttpSession session;

		public RedisHttpSessionRequest(HttpServletRequest request, HttpSession session) {
			super(request);
			this.session = session;
		}

		@Override
		public HttpSession getSession() {
			return session;
		}
	}
}
