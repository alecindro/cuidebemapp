package br.com.cuidebemapp.web.rest.pacientephoto;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.PacientePhoto;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PostTest extends RestBase {

	private String image = "data:image/png;base64,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";

 
	
	@Test
	public void save() throws Exception {
		PacientePhoto pp = new PacientePhoto();
		pp.setDescricao("teste foto");
		pp.setPhoto(image);
		pp.setPaciente(new Paciente(1L));
		pp = post("/api/pacientephotos/noprincipal", pp);
		assertNotNull(pp.getIdpacientephoto());
	}
	
}