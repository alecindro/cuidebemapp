package br.com.cuidebemapp.web.rest.paciente;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

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
public class PacientePost extends RestBase {

	private String baseURL = "/api/pacientes";

	
	@Test
	public void test() throws Exception {
		Paciente paciente = new Paciente();
		paciente.setApelido("");
		paciente.setDatanascimento(new Date());
		paciente.setEnabled(true);
		paciente.setGenero(true);
		paciente.setNome("pacienteTeste1");
		paciente.setStatus(true);
		paciente.setTpestadia(false);
		create(paciente);
		Paciente paciente2 = new Paciente();
		paciente2.setApelido("");
		paciente2.setDatanascimento(new Date());
		paciente2.setEnabled(true);
		paciente2.setGenero(true);
		paciente2.setNome("pacienteTeste2");
		paciente2.setStatus(true);
		paciente2.setTpestadia(false);
		create(paciente2);
	}
	
	public void create(Paciente paciente) throws Exception {
		PacienteDTO pacienteDTO = new PacienteDTO(paciente,null,false,null);
		PacienteDTO result = post(baseURL,pacienteDTO);
		assertThat(result.getPaciente().getNome()).isEqualTo(paciente.getNome());
	}



}
