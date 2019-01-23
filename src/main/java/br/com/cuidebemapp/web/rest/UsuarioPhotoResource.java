package br.com.cuidebemapp.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.UsuarioPhoto;
import br.com.cuidebemapp.service.UsuarioPhotoService;

@RestController
@RequestMapping("/api")
public class UsuarioPhotoResource {

	private final Logger log = LoggerFactory.getLogger(UsuarioPhotoResource.class);

	@Autowired
	private UsuarioPhotoService usuarioPhotoService;

	

	@PostMapping("/usuariophotos")
	@Timed
	public UsuarioPhoto save(@RequestBody UsuarioPhoto usuarioPhoto) {
		log.debug("REST save UsuarioPhoto");
		return usuarioPhotoService.save(usuarioPhoto);
	}
	
	@GetMapping("/usuariophotos/enabled")
	@Timed
	public List<UsuarioPhoto> getEnabled(){
		return usuarioPhotoService.findEnabled();
	}
	
}
