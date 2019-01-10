package br.com.cuidebemapp.config.db;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.cuidebemapp.config.ApplicationProperties;

public class TenantInterceptor extends HandlerInterceptorAdapter {

	public static final String TENANT_HEADER = "X-TenantID";
	@Autowired
	private ApplicationProperties appProperties;
	
	public TenantInterceptor() {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		// ((org.springframework.web.method.HandlerMethod)handler).getMethod().getName()
		// == "authorize";
		String tenant = req.getHeader(TENANT_HEADER);
		TenantContext.setCurrentLocale(req.getLocale());
		boolean tenantSet = false;
		System.out.println("Tentant passando pela seguranca:" + tenant);
		if (StringUtils.isEmpty(tenant)) {
			TenantContext.setCurrentTenant(appProperties.getDefaultTenant());
			tenantSet = true;
			// handleNotAuthorized(res, "{\"error\": \"No tenant supplied\"}");
			// } else if (!SecurityUtils.isCurrentUserInRole(tenant)) {
			// handleNotAuthorized(res, "{\"error\": \"Tenant not accessible to user\"}");
		} else {
			TenantContext.setCurrentTenant(tenant);
			tenantSet = true;
		}
		return tenantSet;
	}
/*
	private void handleNotAuthorized(HttpServletResponse res, String responseBody) throws IOException {
		res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		res.setContentType(MediaType.APPLICATION_JSON_VALUE);
		res.getWriter().write(responseBody);
		res.getWriter().flush();
	}
*/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		TenantContext.clear();
	}
}