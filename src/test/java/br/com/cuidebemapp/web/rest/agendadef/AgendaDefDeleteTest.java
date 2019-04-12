package br.com.cuidebemapp.web.rest.agendadef;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.web.rest.RestBase;
import br.com.foureffect.testrest2.model.PathVariable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AgendaDefDeleteTest extends RestBase{
	
	private String baseURL = "/api/agendadefs";
	
	@Test
	public void test1() throws Exception {
		Long idagendadef = 4L;
		PathVariable pv = new PathVariable();
		pv.addPath(idagendadef);
		delete(baseURL, pv);
	}
	
	
}
