package com.eason.spring.cache;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;



public class CacheCleaner extends TimerTask {
	
	private static final Log LOGGER = LogFactory.getLog(CacheCleaner.class);
	
	@Autowired
	private CacheManager cacheManager;

	@Override
	public void run() {
		for (String name : this.cacheManager.getCacheNames()) {
			this.cacheManager.getCache(name).clear();
			LOGGER.info("Cache <" + name + "> cleared.");
		}
	}
}
