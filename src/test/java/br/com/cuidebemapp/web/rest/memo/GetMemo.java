package br.com.cuidebemapp.web.rest.memo;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Memorando;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetMemo extends RestBase {

	private String baseURL = "/api/memorandos";
	private List<Memorando> memorandos;

	@Test
	public void teste() throws Exception {
		memorandos = get(baseURL, "idpaciente", 1L, Memorando.class);
		assertTrue(memorandos.size() == 1);
	}

}
