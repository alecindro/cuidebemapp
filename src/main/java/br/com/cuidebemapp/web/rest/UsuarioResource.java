package br.com.cuidebemapp.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.Usuario;
import br.com.cuidebemapp.security.AuthoritiesConstants;
import br.com.cuidebemapp.service.UsuarioService;
import br.com.cuidebemapp.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
public class UsuarioResource {

	private final UsuarioService usuarioService;
	private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

	public UsuarioResource(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/usuarios")
	@Timed
	@Secured(AuthoritiesConstants.ADMIN)
	public Usuario save(@Valid @RequestBody Usuario usuario) {
		log.debug("REST request to save usuario : {}", usuario);
		return usuarioService.save(usuario);
	}

	@PutMapping("/usuarios")
	@Timed
	@Secured(AuthoritiesConstants.ADMIN)
	public Usuario updateUsuario(@Valid @RequestBody Usuario usuario) {
		log.debug("REST request to update usuario : {}", usuario);
		if (usuario.getLogin() == null) {
			throw new BadRequestAlertException("", "", "");
		}
		return usuarioService.update(usuario);
	}

	@GetMapping("/usuarios")
	@Timed
	public List<Usuario> getAllUsers() {
		return usuarioService.getUsuarios();
	}

	@GetMapping("/usuarios/{login}")
	@Timed
	public Usuario getUsuario(@PathVariable String login) {
		log.debug("REST request to get Usuario : {}", login);
		return usuarioService.findByLogin(login);
	}

	@DeleteMapping("/usuarios/{login}")
	@Timed
	@Secured(AuthoritiesConstants.ADMIN)
	public Usuario deleteUser(@PathVariable String login) {
		log.debug("REST request to delete Usuario: {}", login);
		return usuarioService.delete(login);
	}

}
