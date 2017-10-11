package com.test.hazelcast;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class HazelcastMain {
	public static void main(String[] args) {
		HazelcastMain mainApp=new HazelcastMain();
		
		String key="val1";
		
		mainApp.addOrUpdateCache(key, "test1");
		String value = mainApp.getValue(key);
		System.out.println("value from cache : "+ value);
		
		mainApp.addOrUpdateCache(key, "test2");
		value = mainApp.getValue(key);
		System.out.println("value after update: "+ value);
		
		mainApp.removeFromCache(key);
		value = mainApp.getValue(key);
		System.out.println("value after remove: "+ value);
	}
	
	private String getValue(String key){
		Cache<String, String> cache = getChacheManager();
		return cache.get(key);
	}
	
	private void removeFromCache(String key){
		Cache<String, String> cache = getChacheManager();
		cache.remove(key);
	}
	
	private void addOrUpdateCache(String key, String value){
		Cache<String, String> cache = getChacheManager();
		cache.put(key, value);
	}
	
	private Cache<String, String> getChacheManager(){
		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager();
		MutableConfiguration<String, String> config
		  = new MutableConfiguration<>();
		Cache<String, String> cache = cacheManager
				  .getCache("simpleCache");
				
		if(cache==null){
			cache = cacheManager
					  .createCache("simpleCache", config);
		}
		
		return cache;
	}
}
