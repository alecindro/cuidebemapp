package br.com.cuidebemapp.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.Evento;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.service.EventoService;
import br.com.cuidebemapp.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
public class EventoResource {

	private final Logger log = LoggerFactory.getLogger(EventoResource.class);
	private final EventoService eventoService;
	
	public EventoResource(EventoService eventoService) {
		this.eventoService = eventoService;
	}
	
	@GetMapping("/eventos")
    @Timed
    public List<Evento> getAllEventosEnabled(@RequestParam("idpaciente") Long idpaciente){
		log.info("get eventos paciente e data");
		Paciente paciente = new Paciente(idpaciente);
		List<Evento> eventos = eventoService.getEventosToday(paciente);
		return eventos;
	}
	
	@PostMapping("/eventos")
	@Timed
	public Evento saveEvento(@RequestBody Evento evento) {
		if(evento.getIdevento() != null) {
			throw new BadRequestAlertException("A new evento cannot already have an ID", Evento.class.getName(), "idexists");
		}
		return eventoService.save(evento);
	}
	
	@PutMapping("/eventos")
	@Timed
	public Evento updateEvento(@RequestBody Evento evento) {
		if(evento.getIdevento() == null) {
			throw new BadRequestAlertException("A update evento cannot already not have an ID", Evento.class.getName(), "idnotexists");
		}
		return eventoService.save(evento);
	}
	
	@DeleteMapping("/eventos")
	@Timed
	public Evento deleteEvento(@RequestBody Evento evento) {
		if(evento.getIdevento() == null) {
			throw new BadRequestAlertException("A evento cannot already not have an ID", Evento.class.getName(), "idnotexists");
		}
		return eventoService.delete(evento);
	}
	
	
}
