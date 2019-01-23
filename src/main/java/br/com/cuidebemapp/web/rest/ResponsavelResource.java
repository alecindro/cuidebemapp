package br.com.cuidebemapp.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.service.ResponsavelService;

@RestController
@RequestMapping("/api")
public class ResponsavelResource {
	
	private final Logger log = LoggerFactory.getLogger(ResponsavelResource.class);

	@Autowired
	private ResponsavelService responsavelService;
	
	@GetMapping("/responsaveis")
	@Timed
	public List<Responsavel> getResponsavelByPaciente(@RequestParam Long idpaciente) {
		log.debug("REST request to get Responsavel by paciente : {}", idpaciente);
		List<Responsavel> responsaveis = responsavelService.findByPaciente(idpaciente);
		return responsaveis;
	}
	
	@PostMapping("/responsaveis")
	@Timed
	public Responsavel save(@RequestBody Responsavel responsavel) {
		return responsavelService.save(responsavel);
	}
	
	@DeleteMapping("/responsaveis/{idresponsavel}")
	@Timed
	public void delete(@PathVariable Long idresponsavel) {
		responsavelService.delete(idresponsavel);
	}


}
