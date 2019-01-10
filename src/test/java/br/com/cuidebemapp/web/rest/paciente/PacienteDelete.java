package br.com.cuidebemapp.web.rest.paciente;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.service.dto.PacienteDTO;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PacienteDelete extends RestBase {

	private String baseURL = "/api/pacientes";
	List<PacienteDTO> pacientes = new ArrayList<>();

	
	@Test
	public void test() throws Exception {
		getEnabled();
		deleteLogic();
	}
	

	public void getEnabled() throws Exception {
		pacientes = getAll(baseURL,PacienteDTO.class);
		for (PacienteDTO pacienteDTO : pacientes) {
			System.out.println(pacienteDTO.toString());
		}
	}

	public void deleteLogic() throws Exception {
		for (PacienteDTO pacienteDTO : pacientes) {
			Paciente paciente = deleteLogic(baseURL, pacienteDTO.getPaciente().getIdpaciente(),Paciente.class);
			assertFalse(paciente.getEnabled());
		}
	}
}
