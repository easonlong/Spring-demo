package com.eason.spring.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eason.spring.dao.entity.User;

@EnableScheduling
@Service(value = "userService")
public class UserService {

	private static final Log LOGGER = LogFactory.getLog(UserService.class);

	private static final Map<String, User> DATA = new HashMap<String, User>();

	static {
		DATA.put("1", new User("1", "eason", "12345678"));
		DATA.put("2", new User("2", "jack", "12345678"));
		DATA.put("3", new User("3", "lucy", "12345678"));
	}

	@Cacheable(value = "userCache")
	public User getUser(String uid) {
		LOGGER.info("Load user from DB, uid=" + uid);
		return DATA.get(uid);
	}

	/**
	 * 将当前更新的user从cache里删除
	 * 
	 * @param user
	 */
	@CacheEvict(value = "userCache", key = "#user.getUid()")
	public void updateUser(User user) {
		DATA.put(user.getUid(), user);
	}

	/**
	 * 定时任务， 每分钟清除一次缓存
	 */
	@Scheduled(cron = "${cache.reload.cron}")
	@CacheEvict(value = "userCache", allEntries = true)
	public void reload() {
		LOGGER.info("Remove all entries from userCache.");
	}
}
