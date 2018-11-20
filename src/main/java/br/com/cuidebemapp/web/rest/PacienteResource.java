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
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.service.PacienteService;
import br.com.cuidebemapp.service.dto.PacienteDTO;
import br.com.cuidebemapp.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
public class PacienteResource {

	private final Logger log = LoggerFactory.getLogger(PacienteResource.class);
	private PacienteService pacienteService;
	
	public PacienteResource(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}
	
	@GetMapping("/pacientes")
    @Timed
    public List<PacienteDTO> getAllPacientesEnabled(){
		log.info("get all pacientes enabled");
		return pacienteService.getPacienteEnabled();
	}
	
	/*@GetMapping("/pacientes")
    @Timed
    public List<PacienteDTO> getAllPacientes(@RequestParam("enabled") boolean enabled){
		log.info("get all pacientes enabled: "+enabled);
		if(enabled) {
		return getAllPacientesEnabled();
		}
		return pacienteService.getPacienteDisabled();
	}*/
	
	@GetMapping("/pacientes/{id}")
	@Timed
	public Paciente getPaciente(@PathVariable("id") Long idpaciente) {
		return pacienteService.getPaciente(idpaciente);
	}
	
	@PostMapping("/pacientes/checkin")
	@Timed
	public PacienteDTO checkin(@RequestBody PacienteDTO pacienteDTO) {
		return pacienteService.check(pacienteDTO, true);
	}
	
	@PostMapping("/pacientes/checkout")
	@Timed
	public PacienteDTO checkout(@RequestBody PacienteDTO pacienteDTO) {
		return pacienteService.check(pacienteDTO, false);
	}
	
	@DeleteMapping("/pacientes/{id}")
	@Timed
	public Paciente delete(@PathVariable("id") Long idpaciente) {
		return pacienteService.delete(idpaciente);
	}
	
	@PostMapping("/pacientes")
	@Timed
	public Paciente save(@RequestBody Paciente paciente) {
		if(paciente.getIdpaciente() != null) {
			throw new BadRequestAlertException("A new paciente cannot already have an ID", Paciente.class.getName(), "idexists");
		}
		return pacienteService.save(paciente);
	}
	
	@PutMapping("/pacientes")
	@Timed
	public Paciente update(@RequestBody Paciente paciente) {
		if(paciente.getIdpaciente() == null) {
			throw new BadRequestAlertException("A update paciente cannot already not have an ID", Paciente.class.getName(), "idnotexists");
		}
		return pacienteService.update(paciente);
	}
}
