package br.com.cuidebemapp.config.liquibase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import br.com.cuidebemapp.config.ApplicationProperties;
import br.com.cuidebemapp.config.db.CurrentTenantIdentifierResolverImpl;
import br.com.cuidebemapp.security.SecurityUtils;
import io.github.jhipster.config.JHipsterConstants;
import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfiguration {
	
	@Autowired
	private Environment env;

	@Autowired
	private ApplicationProperties appProperties;
	
	
	private final Logger log = LoggerFactory.getLogger(LiquibaseConfiguration.class);

	public LiquibaseConfiguration() {
	}
	
	@Bean
	//@DependsOn(value= {"dataSource","entityManagerFactory","authorityRepository"})
	public SpringLiquibase liquibase(@Lazy @Qualifier("dataSource") DataSource dataSource, LiquibaseProperties liquibaseProperties) {

		// Use liquibase.integratio@AutoConfigureAfter(value = {MetricsConfiguration.class, DatabaseConfiguration.class})n.spring.SpringLiquibase if you don't want Liquibase
		// to start asynchronously
		LiquibaseMultiTenantcy liquibase = new LiquibaseMultiTenantcy();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:config/liquibase/uaa.xml");
		liquibase.setContexts(liquibaseProperties.getContexts());
		liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
		liquibase.setDropFirst(liquibaseProperties.isDropFirst());
		liquibase.setShouldRun(false);

		if (env.acceptsProfiles(JHipsterConstants.SPRING_PROFILE_NO_LIQUIBASE)) {
			liquibase.setShouldRun(false);
		} else {
			liquibase.setShouldRun(liquibaseProperties.isEnabled());
			log.debug("Configuring Liquibase");
			liquibase.setUaaSchema(appProperties.getDefaultTenant());
			addSchema(dataSource,liquibase);
		}
		return liquibase;
	}

	private void addSchema(DataSource dataSource, LiquibaseMultiTenantcy liquibase) {
		
		SecurityUtils.getTenants(dataSource, appProperties.getTenantPrefix()).map(this::tenantToSchemaName).forEach(liquibase::addSchema);
		
	}
	
	private String tenantToSchemaName(String schema) {
		return CurrentTenantIdentifierResolverImpl.getSchemaName(schema);
	}
	
	 
}
