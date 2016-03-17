package com.eason.spring.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eason.spring.dao.entity.User;
import com.eason.spring.dao.mapper.UserMapper;

@Service(value = "userService")
public class UserService {

	private static final Log LOGGER = LogFactory.getLog(UserService.class);

	@Autowired
	private UserMapper userMapper;

	@Cacheable(value = "userCache")
	public User getUser(String uid) {
		LOGGER.info("Load user from DB, uid=" + uid);
		return userMapper.getUser(uid);
	}

	/**
	 * 将当前更新的user从cache里删除
	 * 
	 * @param user
	 */
	@CacheEvict(value = "userCache", key = "#user.getUid()")
	public void updateUser(User user) {
		userMapper.update(user);
	}

	/**
	 * 定时任务， 每分钟清除一次缓存
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	@CacheEvict(value = "userCache", allEntries = true)
	public void reload() {
		LOGGER.info("Remove all entries from userCache.");
	}
}
