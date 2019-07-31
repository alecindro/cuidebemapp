package br.com.cuidebemapp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.service.dto.ResidenciaDTO;
import br.com.cuidebemapp.uaa.service.ResidenciaService;

@RestController
@RequestMapping("/api")
public class ResidenciaResource {
	
	private final Logger log = LoggerFactory.getLogger(ResidenciaResource.class);

	@Autowired
	private ResidenciaService residenciaService;
	

	
	@PostMapping("/residencias")
	@Timed
	public ResidenciaDTO checkin(@RequestBody ResidenciaDTO residenciaDTO ) throws Exception {
		log.info("Criando residencia {}.", residenciaDTO.toString());
		return residenciaService.create(residenciaDTO);
	}
	
}
