package br.com.cuidebemapp.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.Agenda;
import br.com.cuidebemapp.service.AgendaService;

@RestController
@RequestMapping("/api")
public class AgendaResource {
	
	@Autowired
	private AgendaService agendaService;
	
	@GetMapping("/agendas")
	@Timed
	public List<Agenda> getAgendaByPaciente(@RequestParam("idpaciente") Long idpaciente, @RequestParam("page") Integer page) {
		return agendaService.getAgendaByPaciente(idpaciente,page).getContent();
	}

}
