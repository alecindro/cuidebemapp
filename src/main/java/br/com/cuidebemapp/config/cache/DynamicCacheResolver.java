package br.com.cuidebemapp.config.cache;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import br.com.cuidebemapp.config.db.CurrentTenantIdentifierResolverImpl;
import br.com.cuidebemapp.config.db.TenantContext;

public class DynamicCacheResolver implements CacheResolver{
	
	  private CacheManager cacheManager;

	    public DynamicCacheResolver(CacheManager cacheManager){
	    	this.cacheManager = cacheManager;
	        
	    }

	@Override
	public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
		 Collection<Cache> caches = new ArrayList<>();
		 String tenantID = CurrentTenantIdentifierResolverImpl.getSchemaName(TenantContext.getCurrentTenant());
		 for(String name : context.getOperation().getCacheNames()) {
			 String cache = name+tenantID;
			 caches.add(cacheManager.getCache(cache));
		 }
		return caches;
	}

}
