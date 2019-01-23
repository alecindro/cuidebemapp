package br.com.cuidebemapp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.ResponsavelPhoto;
import br.com.cuidebemapp.service.ResponsavelPhotoService;

@RestController
@RequestMapping("/api")
public class ResponsavelPhotoResource {

	private final Logger log = LoggerFactory.getLogger(ResponsavelPhotoResource.class);

	@Autowired
	private ResponsavelPhotoService responsavelPhotoService;

	@GetMapping("/responsavelphotos/{idresponsavel}")
	@Timed
	public ResponsavelPhoto getResponsavelPhotoByResponsavel(@PathVariable Long idresponsavel) {
		log.debug("REST request to get ResponsavelPhoto by responsavel : {}", idresponsavel);
		return responsavelPhotoService.findByResponsavel(idresponsavel).orElse(new ResponsavelPhoto());
	}

	@PostMapping("/responsavelphotos")
	@Timed
	public ResponsavelPhoto save(@RequestBody ResponsavelPhoto responsavelPhoto) {
		log.debug("REST save ResponsavelPhoto");
		return responsavelPhotoService.save(responsavelPhoto);
	}
	
	@PutMapping("/responsavelphotos")
	@Timed
	public ResponsavelPhoto update(@RequestBody ResponsavelPhoto responsavelPhoto) {
		log.debug("REST update ResponsavelPhoto");
		return responsavelPhotoService.save(responsavelPhoto);
	}
	
	@DeleteMapping("/responsavelphotos/{idresponsavelphoto}")
	@Timed
	public void delete(@PathVariable Long idresponsavelphoto) {
		log.debug("REST request to delete Usuario: {}", idresponsavelphoto);
		ResponsavelPhoto responsavelPhoto = new ResponsavelPhoto(idresponsavelphoto);
		responsavelPhotoService.delete(responsavelPhoto);
	}

}
