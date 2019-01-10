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
import br.com.cuidebemapp.security.SecurityUtils;
import br.com.cuidebemapp.service.PacienteDTOService;
import br.com.cuidebemapp.service.PacienteService;
import br.com.cuidebemapp.service.dto.PacienteDTO;
import br.com.cuidebemapp.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
public class PacienteResource {

	private final Logger log = LoggerFactory.getLogger(PacienteResource.class);
	private final PacienteDTOService pacienteDTOService;
	private final PacienteService pacienteService;
	
	public PacienteResource(PacienteService pacienteService, PacienteDTOService pacienteDTOService) {
		this.pacienteDTOService = pacienteDTOService;
		this.pacienteService = pacienteService;
	}

	@GetMapping("/pacientes")
	@Timed
	public List<PacienteDTO> getAllPacientesEnabled() {
		log.info("get all pacientes enabled");
		return pacienteDTOService.getPacienteEnabled();
	}

	@GetMapping("/pacientedtos")
	@Timed
	public List<PacienteDTO> getPacientes() {
		log.info("get all pacientes with patoogias enabled");
		return pacienteDTOService.getPacientePatologiaEnabled();
	}

	@GetMapping("/pacientes/{id}")
	@Timed
	public Paciente getPaciente(@PathVariable("id") Long idpaciente) {
		return pacienteService.getPaciente(idpaciente);
	}

	@PostMapping("/pacientes/checkin")
	@Timed
	public PacienteDTO checkin(@RequestBody PacienteDTO pacienteDTO) {
		return pacienteDTOService.check(pacienteDTO, true);
	}

	@PostMapping("/pacientes/checkout")
	@Timed
	public PacienteDTO checkout(@RequestBody PacienteDTO pacienteDTO) {
		return pacienteDTOService.check(pacienteDTO, false);
	}

	@DeleteMapping("/pacientes/{id}")
	@Timed
	public Paciente delete(@PathVariable("id") Long idpaciente) {
		return pacienteService.delete(idpaciente);
	}

	@PostMapping("/pacientes")
	@Timed
	public PacienteDTO save(@RequestBody PacienteDTO pacienteDTO) {
		if (pacienteDTO.getPaciente().getIdpaciente() != null) {
			throw new BadRequestAlertException("A new paciente cannot already have an ID", Paciente.class.getName(),
					"idexists");
		}
		String login = SecurityUtils.getCurrentUserLogin().get();
		return pacienteDTOService.save(pacienteDTO,login);
	}

	@PutMapping("/pacientes")
	@Timed
	public PacienteDTO update(@RequestBody PacienteDTO pacientedto) {
		if (pacientedto.getPaciente().getIdpaciente() == null) {
			throw new BadRequestAlertException("A update paciente cannot already not have an ID",
					Paciente.class.getName(), "idnotexists");
		}
		String login = SecurityUtils.getCurrentUserLogin().get();
		return pacienteDTOService.update(pacientedto,login);
	}
}
