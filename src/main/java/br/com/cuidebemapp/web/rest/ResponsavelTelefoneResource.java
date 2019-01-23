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
import br.com.cuidebemapp.model.ResponsavelTelefone;
import br.com.cuidebemapp.service.ResponsavelTelefoneService;
import br.com.cuidebemapp.web.rest.dto.ResponsavelTelefoneDTO;
import br.com.cuidebemapp.web.rest.dto.ResponsavelTelefoneDTOList;

@RestController
@RequestMapping("/api")
public class ResponsavelTelefoneResource {

	private final Logger log = LoggerFactory.getLogger(ResponsavelTelefoneResource.class);

	@Autowired
	private ResponsavelTelefoneService responsavelTelefoneService;

	@GetMapping("/responsaveltelefones")
	@Timed
	public List<ResponsavelTelefone> getResponsavelTelefoneByResponsavel(@RequestParam Long idresponsavel) {
		log.debug("REST request to get ResponsavelPhoto by responsavel : {}", idresponsavel);
		return responsavelTelefoneService.find(new Responsavel(idresponsavel));
	}

	@PostMapping("/responsaveltelefones")
	@Timed
	public ResponsavelTelefoneDTO save(@RequestBody ResponsavelTelefoneDTO responsavelTelefoneDTO) {
		log.debug("REST save ResponsavelTelefone");
		ResponsavelTelefone responsavelTelefone = responsavelTelefoneService.save(responsavelTelefoneDTO.getResponsavelTelefone());
		responsavelTelefoneDTO.setResponsavelTelefone(responsavelTelefone);
		return responsavelTelefoneDTO;
	}
	
	@PostMapping("/responsaveltelefonesdto")
	@Timed
	public ResponsavelTelefoneDTOList save(@RequestBody ResponsavelTelefoneDTOList responsavelTelefoneDTOs) {
		log.debug("REST save ResponsavelTelefone");
		List<ResponsavelTelefone> responsavelTelefones = responsavelTelefoneService.save(responsavelTelefoneDTOs.getAll());
		responsavelTelefoneDTOs.getAll(responsavelTelefones);
		return responsavelTelefoneDTOs;
	}
	
	@DeleteMapping("/responsaveltelefones/{idresponsaveltelefone}")
	@Timed
	public void delete(@PathVariable Long idresponsaveltelefone) {
		log.debug("REST request to delete responsaveltelefone: {}", idresponsaveltelefone);
		responsavelTelefoneService.delete(idresponsaveltelefone);
	}

}
