package br.com.cuidebemapp.web.rest;

import org.junit.Before;

import br.com.cuidebemapp.config.db.TenantInterceptor;
import br.com.cuidebemapp.security.AuthoritiesConstants;
import br.com.foureffect.testrest2.RestTest;
import br.com.foureffect.testrest2.model.SecurityModel;

public class RestBase extends RestTest{

	private String user = "admin";
	private String password = "admin";
	private String tenant_ID = "tenant_1";
	
	@Before
	public void init() {
		SecurityModel securityModel = new SecurityModel(user,password,TenantInterceptor.TENANT_HEADER,tenant_ID,AuthoritiesConstants.ADMIN);
		init(securityModel);
	}
}
