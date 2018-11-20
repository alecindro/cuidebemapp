package br.com.cuidebemapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cuidebemapp.model.Usuario;
import br.com.cuidebemapp.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario update(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario delete(Usuario usuario) {
		usuario.setEnabled(false);
		return usuarioRepository.save(usuario);
	}
	
	public Usuario delete(String login) {
		Usuario usuario = findByLogin(login);
		usuario.setEnabled(false);
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> getUsuarios(){
		return usuarioRepository.findByEnabled(true);
	}
	
	public List<Usuario> getUsuariosDisabled(){
		return usuarioRepository.findByEnabled(false);
	}
	
	public Usuario findByLogin(String login) {
		return usuarioRepository.findById(login).get();
	}
}
