package br.com.cuidebemapp.config.db;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.cuidebemapp.config.ApplicationProperties;

public class SecurityTenantInterceptor extends HandlerInterceptorAdapter {

	public static final String TENANT_HEADER = "X-TenantID";
	@Autowired
	private ApplicationProperties appProperties;
	
	public SecurityTenantInterceptor() {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
			TenantContext.setCurrentTenant(appProperties.getDefaultTenant());
	
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		TenantContext.clear();
	}
}