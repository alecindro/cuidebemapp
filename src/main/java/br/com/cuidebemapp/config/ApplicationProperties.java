package br.com.cuidebemapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Cuidebemapp.
 * <p>
 * Properties are configured in the application.yml file. See
 * {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

	private String tenantPrefix;
	private String defaultTenant;

	public String getTenantPrefix() {
		return tenantPrefix;
	}

	public void setTenantPrefix(String tenantPrefix) {
		this.tenantPrefix = tenantPrefix;
	}

	public String getDefaultTenant() {
		return defaultTenant;
	}

	public void setDefaultTenant(String defaultTenant) {
		this.defaultTenant = defaultTenant;
	}


}
