package br.com.cuidebemapp.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.Agendadef;
import br.com.cuidebemapp.service.AgendadefService;

@RestController
@RequestMapping("/api")
public class AgendaDefResource {
	
	@Autowired 
	private AgendadefService agendadefService;

	@PostMapping("/agendadefs")
	@Timed
	public Agendadef save(@RequestBody Agendadef agendadef) {
		return agendadefService.save(agendadef);
	}
	
	@GetMapping("/agendadefs")
	@Timed
	public List<Agendadef> getAgendaDefByPaciente(@RequestParam Long idpaciente) {
		return agendadefService.getAgendaDefByPaciente(idpaciente);
	}
	
	@GetMapping("/agendadefs/{idagendadef}")
	@Timed
	public Agendadef getAgendaDef(@PathVariable Long idagendadef) {
		return agendadefService.findAgenda(idagendadef).orElse(new Agendadef());
	}
	
	@PutMapping("/agendadefs")
	@Timed
	public Agendadef updateAgendaDef(@RequestBody Agendadef agendadef) {
		return agendadefService.update(agendadef);
	}
	
	@DeleteMapping("/agendadefs/{idagendadef}")
	@Timed
	public void deleteAgendaDef(@PathVariable Long idagendadef) {
		agendadefService.delete(idagendadef);
	}
	
	
}
