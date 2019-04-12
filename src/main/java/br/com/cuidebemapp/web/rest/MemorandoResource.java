package br.com.cuidebemapp.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.cuidebemapp.model.Memorando;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.service.MemorandoService;
import br.com.cuidebemapp.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
public class MemorandoResource {
	
	private final Logger log = LoggerFactory.getLogger(MemorandoResource.class);
	private final MemorandoService memorandoService;

	public MemorandoResource(MemorandoService memorandoService) {
		this.memorandoService = memorandoService;
	}
	
	@GetMapping(value="/memorandos")
    @Timed
    public List<Memorando> getMemorandos(@RequestParam("idpaciente") long idpaciente){
		Paciente paciente = new Paciente(idpaciente);
		List<Memorando> memorandos = memorandoService.getMemorandosTop30(paciente);
		return memorandos;
	}
	
	@GetMapping(value="/memorandos/{id}")
    @Timed
    public Memorando getMemorando(@PathVariable Long id){
		Memorando memorando = memorandoService.getMemorando(id);
		return memorando;
	}
	
	
	

	@PostMapping("/memorandos")
	@Timed
	public Memorando saveMemorando(@RequestBody Memorando memorando) {
		if(memorando.getIdmemorando() != null) {
			throw new BadRequestAlertException("A new memorando cannot already have an ID", Memorando.class.getName(), "idexists");
		}
		if(memorando.getPaciente() == null || memorando.getPaciente().getIdpaciente() == null) {
			log.error("A new memorando requires a paciente and pacienteID");
			throw new BadRequestAlertException("A new memorando requires a paciente and pacienteID", Memorando.class.getName(), "requirespaciente");
		}
		return memorandoService.save(memorando);
	}
	
	@PutMapping("/memorandos")
	@Timed
	public Memorando updateMemorando(@RequestBody Memorando memorando) {
		if(memorando.getIdmemorando() == null) {
			log.error("A update memorando cannot already not have an ID");
			throw new BadRequestAlertException("A update memorando cannot already not have an ID", Memorando.class.getName(), "idnotexists");
		}
		return memorandoService.update(memorando);
	}
	
	@DeleteMapping("/memorandos/{id}")
	@Timed
	public void deleteEvento(@PathVariable Long id) {
		memorandoService.delete(id);
	}
	
}
