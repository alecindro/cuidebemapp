package br.com.cuidebemapp.web.rest.memo;

import static org.junit.Assert.assertNotNull;

import java.time.OffsetDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Memorando;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.web.rest.RestBase;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PostMemo extends RestBase {

	
	private String baseURL = "/api/memorandos";
	
	
	@Test
	public void teste() throws Exception {
		Memorando memorando = new Memorando();
		memorando.setDataalteracao(OffsetDateTime.now());
		memorando.setDescricao("esse memorando é de teste");
		memorando.setPaciente(new Paciente(1L));
		Memorando  result = post(baseURL,memorando);
		assertNotNull(result.getIdmemorando());
	}


}
