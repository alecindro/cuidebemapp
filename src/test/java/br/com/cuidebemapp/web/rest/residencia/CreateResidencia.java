package br.com.cuidebemapp.web.rest.residencia;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.config.db.tenant.TenantInterceptor;
import br.com.cuidebemapp.security.AuthoritiesConstants;
import br.com.cuidebemapp.service.dto.AdminDTO;
import br.com.cuidebemapp.service.dto.ResidenciaDTO;
import br.com.cuidebemapp.uaa.model.Residencia;
import br.com.foureffect.testrest2.RestTest;
import br.com.foureffect.testrest2.model.SecurityModel;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CreateResidencia extends RestTest{

	private String baseURL = "/api/residencias";
	
	private String user = "admin";
	private String password = "admin";
	private String tenant_ID = "uaa";
	
	@Before
	public void init() {
		SecurityModel securityModel = new SecurityModel(user,password,TenantInterceptor.TENANT_HEADER,tenant_ID,AuthoritiesConstants.ADMIN);
		init(securityModel);
	}



	@Test
	public void teste() throws Exception {
		ResidenciaDTO residenciaDTO = new ResidenciaDTO();
		
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setFirstName("userTeste3");
		adminDTO.setLastName("sobrenome3");
		adminDTO.setEmail("alecindrocastilho3@gmail.com");
		adminDTO.setPassword("teste");
		residenciaDTO.setAdminDTO(adminDTO);;
		Residencia residencia = new Residencia();
		residencia.setRazao("testeresidencia");
		residenciaDTO.setResidencia(residencia);
		createResidencia(residenciaDTO);
	}
	
	private void createResidencia(ResidenciaDTO residenciaDTO) throws Exception {
		
		residenciaDTO = post(baseURL,residenciaDTO, ResidenciaDTO.class);
		assertTrue(residenciaDTO.getResidencia().getIdresidencia() != null);
	}
	
	
}
