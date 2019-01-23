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

import br.com.cuidebemapp.model.Usuario;
import br.com.cuidebemapp.model.UsuarioTelefone;
import br.com.cuidebemapp.service.UsuarioTelefoneService;
import br.com.cuidebemapp.web.rest.dto.UsuarioTelefoneDTOList;

@RestController
@RequestMapping("/api")
public class UsuarioTelefoneResource {

	private final Logger log = LoggerFactory.getLogger(UsuarioTelefoneResource.class);

	@Autowired
	private UsuarioTelefoneService usuarioTelefoneService;

	@GetMapping("/usuariotelefones")
	@Timed
	public List<UsuarioTelefone> getResponsavelTelefoneByResponsavel(@RequestParam String login) {
		log.debug("REST request to get UsuarioTelefone by usuario : {}", login);
		return usuarioTelefoneService.findAll(new Usuario(login));
	}

	@PostMapping("/usuariotelefones")
	@Timed
	public UsuarioTelefone save(@RequestBody UsuarioTelefone usuarioTelefone) {
		log.debug("REST save UsuarioTelefone");
		return usuarioTelefoneService.save(usuarioTelefone);
	}
	
	
	@DeleteMapping("/usuariotelefones/{idusuariotelefone}")
	@Timed
	public void delete(@PathVariable Long idusuariotelefone) {
		log.debug("REST request to delete usuariotelefone: {}", idusuariotelefone);
		usuarioTelefoneService.delete(idusuariotelefone);
	}
	
	@PostMapping("/usuariotelefonesdto")
	@Timed
	public UsuarioTelefoneDTOList save(@RequestBody UsuarioTelefoneDTOList usuarioTelefoneDTOs) {
		log.debug("REST save ResponsavelTelefone");
		List<UsuarioTelefone> usuarioTelefones = usuarioTelefoneService.save(usuarioTelefoneDTOs.getAll());
		usuarioTelefoneDTOs.getAll(usuarioTelefones);
		return usuarioTelefoneDTOs;
	}

}
