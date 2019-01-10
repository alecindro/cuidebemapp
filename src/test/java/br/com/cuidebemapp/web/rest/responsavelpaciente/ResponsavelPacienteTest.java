package br.com.cuidebemapp.web.rest.responsavelpaciente;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.model.ResponsavelPaciente;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ResponsavelPacienteTest  extends RestBase {

	private String baseURL = "/api/responsavelpacientes/";
	
	@Test
	public void teste() throws Exception {
		ResponsavelPaciente rp = new ResponsavelPaciente();
		Paciente paciente = new Paciente(1L);
		rp.setPaciente(paciente);
		Responsavel responsavel = new Responsavel();
		responsavel.setNome("testeBack");
		rp.setResponsavel(responsavel);
		ResponsavelPaciente result = post(baseURL,rp);
		System.out.println("ID rp " +result.getIdresponsavelPaciente());
	}
	
	@Test
	public void teste2() throws Exception {
		Long idpaciente = 1L;
		baseURL = baseURL+idpaciente;
		List<ResponsavelPaciente> rps = get(baseURL, "idpaciente", idpaciente, ResponsavelPaciente.class);
		System.out.println("Size "+rps.size());
		for(ResponsavelPaciente rp : rps) {
			System.out.println(rp.getResponsavel().getNome());
		}
	}


}
