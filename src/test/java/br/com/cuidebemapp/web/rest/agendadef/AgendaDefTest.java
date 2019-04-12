package br.com.cuidebemapp.web.rest.agendadef;

import static org.junit.Assert.assertNotNull;

import java.time.OffsetDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Agendadef;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AgendaDefTest extends RestBase{
	
	private String baseURL = "/api/agendadefs";
	
	public void test1() throws Exception {
		Agendadef agendadef = new Agendadef();
		agendadef.setGrupoevento("Respiratório");
		agendadef.setSubgrupoevento("Frequência Cardíaca");
		agendadef.setPaciente(new Paciente(1L));
		agendadef.setDiaspersonalizado(false);
		agendadef.setHorario("11:00");
		agendadef.setDatainicio(OffsetDateTime.now());
		agendadef.setDatafim(OffsetDateTime.now().plusDays(4));
		Agendadef saved = post(baseURL,agendadef);
		assertNotNull(saved);
		assertNotNull(saved.getIdagendadef());
	}
	
	@Test
	public void test2() throws Exception {
		Agendadef agendadef = new Agendadef();
		agendadef.setGrupoevento("Sinais Vitais");
		agendadef.setSubgrupoevento("Temperatura Corporal");
		agendadef.setPaciente(new Paciente(1L));
		agendadef.setDiaspersonalizado(true);
		agendadef.setHorario("12:00");
		agendadef.setDias(new Integer[] {2,4,6});
		agendadef.setDatainicio(OffsetDateTime.now());
		agendadef.setDatafim(OffsetDateTime.now().plusDays(8));
		Agendadef saved = post(baseURL,agendadef);
		assertNotNull(saved);
		assertNotNull(saved.getIdagendadef());
	}

}
