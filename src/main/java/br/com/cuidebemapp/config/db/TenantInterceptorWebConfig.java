package br.com.cuidebemapp.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TenantInterceptorWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(tenantInterceptor()).addPathPatterns("/api/**");
    	registry.addInterceptor(secTenantInterceptor()).addPathPatterns("/sec/**");
   }
    
    @Bean
    public SecurityTenantInterceptor secTenantInterceptor() {
    	return new SecurityTenantInterceptor();
    }
    
    @Bean
    public TenantInterceptor tenantInterceptor() {
    	return new TenantInterceptor();
    }

}