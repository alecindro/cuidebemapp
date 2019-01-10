package br.com.cuidebemapp.web.rest.pacientephoto;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.PacientePhoto;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class GetTest extends RestBase {

	
	
	@Test
	public void getAllPhotoNoPrincipal() throws Exception {
		List<PacientePhoto> list = get("/api/pacientephotos/noprincipal/today", "idPaciente", 1L, PacientePhoto.class);
		for(PacientePhoto pp : list) {
			System.out.println(pp.toString());
		}
	}
}
