package br.com.cuidebemapp.config.db.cache;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Properties;

import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.spi.CachingProvider;

import io.github.jhipster.config.jcache.NoDefaultJCacheRegionFactory;

public class BeanClassLoaderAwareJCacheRegionFactory extends NoDefaultJCacheRegionFactory{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static volatile ClassLoader classLoader;

	    @Override
	    protected CacheManager getCacheManager(Properties properties) {
	        Objects.requireNonNull(classLoader, "Please set Spring's classloader in the setBeanClassLoader " +
	            "method before using this class in Hibernate");

	        CachingProvider cachingProvider = getCachingProvider( properties );
	        String cacheManagerUri = getProp( properties, CONFIG_URI );

	        URI uri = getUri(cachingProvider, cacheManagerUri);
	        CacheManager cacheManager = cachingProvider.getCacheManager(uri, classLoader);

	        // To prevent some class loader memory leak this might cause
	        setBeanClassLoader(null);

	        return cacheManager;
	    }

	    private URI getUri(CachingProvider cachingProvider, String cacheManagerUri) {
	        URI uri;
	        if (cacheManagerUri != null) {
	            try {
	                uri = new URI(cacheManagerUri);
	            }
	            catch (URISyntaxException e) {
	                throw new CacheException("Couldn't create URI from " + cacheManagerUri, e);
	            }
	        }
	        else {
	            uri = cachingProvider.getDefaultURI();
	        }
	        return uri;
	    }

	    /**
	     * This method must be called from a Spring Bean to get the classloader.
	     * For example: BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
	     *
	     * @param classLoader The Spring classloader
	     */
	    public static void setBeanClassLoader(ClassLoader classLoader) {
	        BeanClassLoaderAwareJCacheRegionFactory.classLoader = classLoader;
	    }
	}
