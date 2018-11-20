package br.com.cuidebemapp.config.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class MultitenantCacheManager implements CacheManager {

	
	
	private final Logger logger = LoggerFactory.getLogger(MultitenantCacheManager.class);	
	
	private Map<String, Cache> caches = new HashMap<>();

	public MultitenantCacheManager(CacheManager cacheManager) {
		for(String cacheName : cacheManager.getCacheNames()) {
			Cache cache = cacheManager.getCache(cacheName);
			MultitenantCache  multitenantCache = new MultitenantCache(cache);
			caches.put(cacheName, multitenantCache);
		}
	}
	
		@Override
	public Cache getCache(String name) {
		Cache cache = caches.get(name);
		return cache;
	}
		
	

	@Override
	public Collection<String> getCacheNames() {
		return caches.keySet();
	}
}