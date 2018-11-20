package br.com.cuidebemapp.config.liquibase;

import java.util.HashSet;
import java.util.Set;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

public class LiquibaseMultiTenantcy extends SpringLiquibase {

    protected Set<String> schemas;
    private String uaaSchema;

    @Override
    public void afterPropertiesSet() throws LiquibaseException {
        String defaultSchema = getDefaultSchema();

        for (String schema : schemas) {
            setDefaultSchema(schema);
            setChangeLog("classpath:config/liquibase/master.xml");
    		super.afterPropertiesSet();
        }
        if (uaaSchema != null) {
        	genUaaSchema(uaaSchema);
        }
        setDefaultSchema(defaultSchema);
    }
    
    public void setUaaSchema(String uaaSchema) {
    	this.uaaSchema = uaaSchema;
    }
    
    private void genUaaSchema(String uaaSchema) throws LiquibaseException {
    	setDefaultSchema(uaaSchema);
        setChangeLog("classpath:config/liquibase/uaa.xml");
        super.afterPropertiesSet();
    }

    public void addSchema(String schema) {
        if (this.schemas == null) {
            this.schemas = new HashSet<>();
        }
        this.schemas.add(schema);
    }
}
