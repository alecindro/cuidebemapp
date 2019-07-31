package br.com.cuidebemapp.config.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import br.com.cuidebemapp.uaa.config.UAAJpaProperties;
import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableJpaRepositories(
		  basePackages = "br.com.cuidebemapp.uaa", 
		    entityManagerFactoryRef = "entityManagerFactoryUAA", 
		    transactionManagerRef = "transactionManagerUAA"
		)
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class UAADatabaseConfiguration {

	@Autowired
	private UAAJpaProperties uaaJpaProperties;

	@Bean(name = "uaaDataSource")
	 @ConfigurationProperties(prefix = "uaa.datasource")
	  public DataSource dataSourceUAA() {
		HikariDataSource ds = DataSourceBuilder.create().type(HikariDataSource.class).build();
		ds.setAutoCommit(false);
		return ds;
	  }
	  
	  @Bean(name = "entityManagerFactoryUAA")
	  public LocalContainerEntityManagerFactoryBean 
	  entityManagerFactoryUAA(
	    EntityManagerFactoryBuilder builder,
	    @Qualifier("uaaDataSource") DataSource dataSource
	  ) {
		  
		    Map<String, Object> properties = new HashMap<>();
		    properties.putAll(uaaJpaProperties.getProperties());
		  LocalContainerEntityManagerFactoryBean em =  builder
	      .dataSource(dataSource)
	      .packages(new String[]{"br.com.cuidebemapp.uaa.model"})
	      .persistenceUnit("uaa")
	      .build();
		  em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		  em.setJpaPropertyMap(properties);
		  return em;
	  }
	    
	  @Bean(name = "transactionManagerUAA")
	  public PlatformTransactionManager transactionManagerUAA(
	    @Qualifier("entityManagerFactoryUAA") EntityManagerFactory 
	    entityManagerFactoryUAA
	  ) {
	    return new JpaTransactionManager(entityManagerFactoryUAA);
	  }
	  
	  @Bean
		public SpringLiquibase liquibaseUAA(@Lazy @Qualifier("uaaDataSource") DataSource uaaDataSource, LiquibaseProperties liquibaseProperties) {

			// Use liquibase.integratio@AutoConfigureAfter(value = {MetricsConfiguration.class, DatabaseConfiguration.class})n.spring.SpringLiquibase if you don't want Liquibase
			// to start asynchronously
			SpringLiquibase liquibase = new SpringLiquibase();
			String schema = uaaJpaProperties.getHibernateProperties(uaaDataSource).get("hibernate.default_schema");
			verifySchema(uaaDataSource, schema);
			liquibase.setDataSource(uaaDataSource);
			liquibase.setChangeLog("classpath:config/liquibase/masterUAA.xml");
			liquibase.setContexts(liquibaseProperties.getContexts());
			liquibase.setDefaultSchema(schema);
			liquibase.setDropFirst(liquibaseProperties.isDropFirst());
			liquibase.setShouldRun(liquibaseProperties.isEnabled());
			return liquibase;
		}
	  
	  private void verifySchema(DataSource dataSource, String schema) {
			String sql = "SELECT schema_name FROM information_schema.schemata WHERE schema_name = ?";
			Connection conn = null;

			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, schema);
				ResultSet rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) {
					sql = "CREATE schema " + schema;
					ps = conn.prepareStatement(sql);
					ps.execute();
					if (!conn.getAutoCommit()) {
						conn.commit();
					}
				}
				ps.close();

			} catch (SQLException e) {
				throw new RuntimeException(e);

			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
		}

}
