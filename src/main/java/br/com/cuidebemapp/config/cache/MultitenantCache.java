package br.com.cuidebemapp.config.cache;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.util.Assert;

import br.com.cuidebemapp.config.db.tenant.TenantContext;

public final class MultitenantCache implements Cache {

	private final Logger logger = LoggerFactory.getLogger(MultitenantCache.class);	
	public final Cache delegate;


	public MultitenantCache(final Cache delegate) {
		this.delegate = delegate;
	}

	@Override
	public String getName() {
		return this.delegate.getName();
	}

	@Override
	public Object getNativeCache() {
		return this.delegate.getNativeCache();
	}

	@Override
	public ValueWrapper get(Object key) {
		Object translatedKey = translateKey(key);
		return this.delegate.get(translatedKey);
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		Object translatedKey = translateKey(key);
		return delegate.get(translatedKey, type);
	}

	@Override
	public <T> T get(Object key, Callable<T> callable) {
		ValueWrapper val = delegate.get(key);
		if (val != null) {
			return (T) val.get();
		}

		try {
			return  callable.call();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void put(Object key, Object value) {
		Object translatedKey = translateKey(key);
		this.delegate.put(translatedKey, value);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		Object translatedKey = translateKey(key);
		return delegate.putIfAbsent(translatedKey, value);
	}

	@Override
	public void evict(Object key) {
		Object translatedKey = translateKey(key);
		this.delegate.evict(translatedKey);
	}

	@Override
	public void clear() {
		this.delegate.clear();
	}

	private String translateKey(Object key)  {
		Assert.notNull(key, "Key must have some value");

		logger.trace("Translating key {}", key);
		String tenantContext = TenantContext.getCurrentTenant();

		Assert.hasText(tenantContext, "Tenant context is required but is not available");

		return tenantContext + ":" + this.getName() + ":" + key;
	}
}
