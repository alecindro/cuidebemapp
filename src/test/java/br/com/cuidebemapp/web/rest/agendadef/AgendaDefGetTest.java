package br.com.cuidebemapp.web.rest.agendadef;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Agendadef;
import br.com.cuidebemapp.web.rest.RestBase;
import br.com.foureffect.testrest2.model.RequestParam;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AgendaDefGetTest extends RestBase{
	
	private String baseURL = "/api/agendadefs";
	
	@Test
	public void test1() throws Exception {
		Long idpaciente = 1L;
		RequestParam param = new RequestParam();
		param.addParams("idpaciente", idpaciente);
		List<Agendadef> result = get(baseURL,param,Agendadef.class);
		for(Agendadef agendadef : result) {
			System.out.println(agendadef.toString());
		}
	}
	
	
}
