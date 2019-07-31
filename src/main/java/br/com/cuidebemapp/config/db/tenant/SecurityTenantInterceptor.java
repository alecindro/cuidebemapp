package br.com.cuidebemapp.config.db.tenant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityTenantInterceptor extends HandlerInterceptorAdapter {

	public static final String TENANT_HEADER = "X-TenantID";
	
	
	public SecurityTenantInterceptor() {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
			TenantContext.setCurrentTenant("uaa");
	
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		TenantContext.clear();
	}
}