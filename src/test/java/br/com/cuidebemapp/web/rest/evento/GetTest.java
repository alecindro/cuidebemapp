package br.com.cuidebemapp.web.rest.evento;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Evento;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetTest extends RestBase{
	
	@Test
	public void getAllEventosEnabled() throws Exception {
		List<Evento> eventos = get("/api/eventos","idpaciente",1L,Evento.class);
		for (Evento result : eventos) {
			System.out.println(result.toString());
		}
	}

}
