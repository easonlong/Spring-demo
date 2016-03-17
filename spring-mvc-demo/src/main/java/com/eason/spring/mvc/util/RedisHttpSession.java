package com.eason.spring.mvc.util;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

@SuppressWarnings("deprecation")
public class RedisHttpSession implements HttpSession {
	public static final String COOKIE_NAME_SESSION_ID = "_rdsid";
	private static final String REDIS_KEY_SESSION_IDGEN = "~sngen`";
	private static final String REDIS_KEY_SESSION_PREFIX = "~sn`";
	private static final String REDIS_FIELD_CREATE_TIME = "~cttime`";
	private static final int SESSION_EXPIRE_SECONDS = 60 * 45;

	private RedisUtil redisUtil;

	private String sessionId, redisKey;
	private long sessionCreateTime;
	private boolean isNewSession;

	public RedisHttpSession(HttpServletRequest request, HttpServletResponse response, StringRedisTemplate redisTemplate) {
		this.redisUtil = new RedisUtil(redisTemplate);

		String sessionId = WebUtil.getCookie(request, COOKIE_NAME_SESSION_ID);
		String stime = redisUtil.hget(REDIS_KEY_SESSION_PREFIX + sessionId, REDIS_FIELD_CREATE_TIME);
		if (stime == null)
			sessionId = null;
		else
			this.sessionCreateTime = Long.parseLong(stime);

		if (sessionId == null) {
			sessionId = newSessionId();
			this.isNewSession = true;
			this.redisKey = REDIS_KEY_SESSION_PREFIX + sessionId;

			this.sessionCreateTime = System.currentTimeMillis();
			redisUtil.hset(redisKey, REDIS_FIELD_CREATE_TIME, Long.toString(sessionCreateTime));
			WebUtil.setCookie(COOKIE_NAME_SESSION_ID, sessionId, true, request, response);
		} else {
			this.sessionId = sessionId;
			this.isNewSession = false;
			this.redisKey = REDIS_KEY_SESSION_PREFIX + sessionId;
		}
		redisUtil.expire(redisKey, SESSION_EXPIRE_SECONDS);
	}

	private String newSessionId() {
		String uq = Long.toHexString(redisUtil.incr(REDIS_KEY_SESSION_IDGEN));
		if (uq.length() < 15)
			uq += "." + RandomStringUtils.randomAlphanumeric(15 - uq.length());
		return uq;
	}

	@Override
	public String getId() {
		return sessionId;
	}

	@Override
	public void invalidate() {
		redisUtil.del(redisKey);
	}

	@Override
	public boolean isNew() {
		return isNewSession;
	}

	@Override
	public Object getAttribute(String name) {
		return redisUtil.hget(redisKey, name);
	}

	@Override
	public Object getValue(String name) {
		return getAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		if (value == null)
			redisUtil.hdel(redisKey, name);
		else if (!(value instanceof String))
			throw new IllegalArgumentException("RedisHttpSession only support 'String' value.");
		else
			redisUtil.hset(redisKey, name, (String) value);
	}

	@Override
	public void putValue(String name, Object value) {
		setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		redisUtil.hdel(redisKey, name);
	}

	@Override
	public void removeValue(String name) {
		removeAttribute(name);
	}

	@Override
	public long getCreationTime() {
		return sessionCreateTime;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public String[] getValueNames() {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public long getLastAccessedTime() {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public ServletContext getServletContext() {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public int getMaxInactiveInterval() {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public HttpSessionContext getSessionContext() {
		throw new RuntimeException("Not implemented.");
	}

}
