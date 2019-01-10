package br.com.cuidebemapp.web.rest.evento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.OffsetDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Evento;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.Usuario;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PostTest extends RestBase{

	private String baseURL = "/api/eventos";
	
	@Test
	public void saveEvento() throws Exception {
		//INSERT INTO `evento` VALUES (1,1,36,NULL,'2017-08-08 09:00:10','2017-08-08 09:00:10',
		//'1','','Nutrição','Café da manhã','Aceitou bem',0,NULL,NULL,NULL,NULL,NULL,NULL)
		Evento evento = new Evento();
		evento.setPaciente(new Paciente(1L));
		evento.setUsuario(new Usuario("admin"));
		evento.setDescevento("");
		evento.setDataevento(OffsetDateTime.now());
		evento.setDataregistro(OffsetDateTime.now());
		evento.setEnabled(true);
		evento.setGrupoevento("Nutrição");
		evento.setSubgrupoevento("Café da manhã");
		evento.setValue(0);
		evento.setDescricao("Aceitou bem");
		Evento result = post(baseURL,evento);
		assertTrue(result.getIdevento() != null);
		assertEquals(evento.getDescricao(),result.getDescricao());
		assertEquals(evento.getPaciente().getIdpaciente(),result.getPaciente().getIdpaciente());
	}



}
