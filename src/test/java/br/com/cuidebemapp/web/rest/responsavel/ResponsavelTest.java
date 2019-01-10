package br.com.cuidebemapp.web.rest.responsavel;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ResponsavelTest  extends RestBase {

	private String baseURL = "/api/responsavel/paciente/";
	
	@Test
	public void teste() throws Exception {
		Long idpaciente = 1L;
		baseURL = baseURL+idpaciente;
		List<Responsavel> responsaveis = get(baseURL, "idpaciente", idpaciente, Responsavel.class);
		System.out.println("Size "+responsaveis.size());
		for(Responsavel responsavel : responsaveis) {
			System.out.println(responsavel.getNome());
		}
	}

}
