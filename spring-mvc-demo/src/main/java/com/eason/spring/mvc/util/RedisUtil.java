package com.eason.spring.mvc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis 常用命令封装
 */
public final class RedisUtil {
	private StringRedisTemplate redisTemplate;

	public RedisUtil(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public long incr(String key) {
		final byte[] binKey = str2bin(key);
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.incr(binKey);
			}
		}).longValue();
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> requiredType) {
		return (T) get(key);
	}

	public Object get(String key) {
		final byte[] binKey = str2bin(key);
		byte[] binData = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(binKey);
			}
		});

		if (binData == null)
			return null;
		return bin2object(binData);
	}

	public void set(String key, Object value) {
		final byte[] binKey = str2bin(key);
		final byte[] binData = object2bin((Serializable) value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(binKey, binData);
				return null;
			}
		});
	}

	public void publish(String channel, Object message) {
		final byte[] binChannel = str2bin(channel);
		final byte[] binMessage = object2bin((Serializable) message);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.publish(binChannel, binMessage);
				return null;
			}
		});
	}

	public void setEx(String key, final long seconds, Object value) {
		final byte[] binKey = str2bin(key);
		final byte[] binData = object2bin((Serializable) value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(binKey, seconds, binData);
				return null;
			}
		});
	}

	public void hset(String key, String field, String value) {
		final byte[] binKey = str2bin(key);
		final byte[] binField = str2bin(field);
		final byte[] binValue = str2bin(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hSet(binKey, binField, binValue);
				return null;
			}
		});
	}

	public String hget(String key, String field) {
		final byte[] binKey = str2bin(key);
		final byte[] binField = str2bin(field);
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] binValue = connection.hGet(binKey, binField);
				return bin2str(binValue);
			}
		});
	}

	public void expire(String key, final long seconds) {
		final byte[] binKey = str2bin(key);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.expire(binKey, seconds);
				return null;
			}
		});
	}

	public void del(String key) {
		final byte[] binKey = str2bin(key);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.del(binKey);
				return null;
			}
		});
	}

	public void hdel(String key, String field) {
		final byte[] binKey = str2bin(key);
		final byte[] binField = str2bin(field);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hDel(binKey, binField);
				return null;
			}
		});
	}

	public static byte[] str2bin(String str) {
		if (str == null)
			return null;
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String bin2str(byte[] bin) {
		if (bin == null)
			return null;
		try {
			return new String(bin, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private static byte[] object2bin(Serializable obj) {
		if (obj == null)
			return null;
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(stream);
			oos.writeObject(obj);
			oos.close();
			return stream.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object bin2object(byte[] binData) {
		if (binData == null)
			return null;
		ByteArrayInputStream stream = new ByteArrayInputStream(binData);
		try {
			ObjectInputStream ois = new ObjectInputStream(stream);
			Object ret = ois.readObject();
			ois.close();
			return ret;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
