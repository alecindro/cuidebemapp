package br.com.cuidebemapp.config.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cuidebemapp.config.ApplicationProperties;
import br.com.cuidebemapp.config.db.CurrentTenantIdentifierResolverImpl;
import br.com.cuidebemapp.config.db.cache.BeanClassLoaderAwareJCacheRegionFactory;
import br.com.cuidebemapp.security.SecurityUtils;
import io.github.jhipster.config.JHipsterProperties;

@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport{

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;
    

	@Autowired
	private ApplicationProperties appProperties;
    

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }
    
 
 
    
    
    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer(DataSource dataSource) {
    	 
        return cm -> {
        	List<String> tenants = SecurityUtils.getTenants(dataSource, appProperties.getTenantPrefix()).collect(Collectors.toList());
        	for (String tenant : tenants ) {
            createCache(cm,br.com.cuidebemapp.repository.AgendaRepository.NEXT_AGENDA_CACHE, tenant);
            createCache(cm,br.com.cuidebemapp.repository.EventoRepository.MAX_EVENTO, tenant);
            createCache(cm,br.com.cuidebemapp.repository.EventoRepository.EVENTO_PACIENTE, tenant);
            createCache(cm,br.com.cuidebemapp.repository.EventoRepository.EVENTO_PACIENTE_TOP30, tenant);
            createCache(cm,br.com.cuidebemapp.repository.EventoRepository.EVENTO_TIMELINE, tenant);
            createCache(cm,br.com.cuidebemapp.repository.PacientePhotoRepository.PACIENTE_PHOTO, tenant);
            createCache(cm,br.com.cuidebemapp.repository.PacientePhotoRepository.PACIENTE_PHOTO_ALL, tenant);
            createCache(cm,br.com.cuidebemapp.repository.PacientePhotoRepository.PACIENTE_PHOTO_BY_PACIENTE, tenant);
            createCache(cm,br.com.cuidebemapp.repository.PacientePhotoRepository.PACIENTE_PHOTO_BY_USER, tenant);
            createCache(cm,br.com.cuidebemapp.repository.PacienteRepository.PACIENTES_ENABLED_CACHE, tenant);
        	} 
        	//UAA caches
        	cm.createCache(br.com.cuidebemapp.repository.UserRepository.USERS_BY_LOGIN_CACHE,jcacheConfiguration);
        	cm.createCache(br.com.cuidebemapp.repository.UserRepository.USERS_BY_EMAIL_CACHE,jcacheConfiguration);
        	
        	cm.createCache(br.com.cuidebemapp.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.cuidebemapp.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.cuidebemapp.domain.User.class.getName() + ".authorities", jcacheConfiguration);
                        
            // jhipster-needle-ehcache-add-entry
        };
    }
    
    @Bean(name = "dynCacheResolver")
    public CacheResolver cacheResolver(CacheManager cacheManager){
      CacheResolver cacheResolver = new DynamicCacheResolver(cacheManager);
      return cacheResolver;
    }
    private String tenantToSchemaName(String schema) {
		return CurrentTenantIdentifierResolverImpl.getSchemaName(schema);
	}
    
    private void createCache(javax.cache.CacheManager cm,String cacheName, String tenantID) {
    	 String tenant = tenantToSchemaName(tenantID);
    	cm.createCache(cacheName+tenant, jcacheConfiguration);
    }
    
    
}
