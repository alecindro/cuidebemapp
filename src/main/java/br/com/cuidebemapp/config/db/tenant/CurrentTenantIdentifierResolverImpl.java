package br.com.cuidebemapp.config.db.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cuidebemapp.config.ApplicationProperties;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

	@Autowired
	private ApplicationProperties appProperties;
	
	public static String getSchemaName(String tenantId) {
		return tenantId.toLowerCase();
	}

	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenantId = TenantContext.getCurrentTenant();
		 if (tenantId != null) {
		return getSchemaName(tenantId);
		 }
		 return appProperties.getDefaultTenant();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}