package br.com.cuidebemapp.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.model.ResponsavelPaciente;
import br.com.cuidebemapp.service.ResponsavelPacienteService;
import br.com.cuidebemapp.service.ResponsavelService;

@RestController
@RequestMapping("/api")
public class ResponsavelResource {
	
	private final Logger log = LoggerFactory.getLogger(ResponsavelResource.class);

	@Autowired
	private ResponsavelPacienteService responsavelPacienteService;
	@Autowired
	private ResponsavelService responsavelService;
	
	@GetMapping("/responsaveis/paciente/{idpaciente}")
	@Timed
	public List<Responsavel> getResponsavelByPaciente(@PathVariable Long idpaciente) {
		log.debug("REST request to get Responsavel by paciente : {}", idpaciente);
		List<ResponsavelPaciente> list = responsavelPacienteService.findByPaciente(idpaciente);
		List<Responsavel> responsaveis = new ArrayList<>();
		for(ResponsavelPaciente rp : list) {
			responsaveis.add(rp.getResponsavel());
		}
		return responsaveis;
	}
	
	@PostMapping("/responsaveis")
	@Timed
	public Responsavel save(@RequestBody Responsavel responsavel) {
		return responsavelService.save(responsavel);
	}


}
