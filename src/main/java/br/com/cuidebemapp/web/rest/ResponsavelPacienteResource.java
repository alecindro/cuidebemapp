package br.com.cuidebemapp.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.ResponsavelPaciente;
import br.com.cuidebemapp.service.ResponsavelPacienteService;

@RestController
@RequestMapping("/api")
public class ResponsavelPacienteResource {
	
	private final Logger log = LoggerFactory.getLogger(ResponsavelPacienteResource.class);

	
	
	@Autowired
	private ResponsavelPacienteService responsavelPacienteService;
	
	
	@GetMapping("/responsavelpacientes/{idpaciente}")
	@Timed
	public List<ResponsavelPaciente> getResponsavelByPaciente(@PathVariable Long idpaciente) {
		log.debug("REST request to get Responsavel by paciente : {}", idpaciente);
		return  responsavelPacienteService.findByPaciente(idpaciente);
	}
	
	@GetMapping("/responsavelpacientes")
	@Timed
	public ResponsavelPaciente getResponsavelByPacienteandResponsavel(@RequestParam Long idpaciente, @RequestParam Long idresponsavel) {
		log.debug("REST request to get ResponsavelPaciente by paciente : {}", idpaciente+" and responsavel: "+idresponsavel);
		return  responsavelPacienteService.findByPacienteandResponsvel(idpaciente, idresponsavel);
	}
	
	@PostMapping("/responsavelpacientes")
	@Timed
	public ResponsavelPaciente save(@RequestBody ResponsavelPaciente responsavelPaciente) {
		return responsavelPacienteService.save(responsavelPaciente);
	}
	
	@PutMapping("/responsavelpacientes")
	@Timed
	public ResponsavelPaciente update(@RequestBody ResponsavelPaciente responsavelPaciente) {
		return responsavelPacienteService.save(responsavelPaciente);
	}


}
