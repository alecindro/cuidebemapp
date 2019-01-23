package br.com.cuidebemapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.Telefone;
import br.com.cuidebemapp.model.Usuario;
import br.com.cuidebemapp.model.UsuarioTelefone;
import br.com.cuidebemapp.repository.UsuarioTelefoneRepository;

@Service
@Transactional
public class UsuarioTelefoneService {
	
	@Autowired
	private UsuarioTelefoneRepository usuarioTelefoneRepository;

	@Autowired
	private TelefoneService telefoneService;
	public void delete(Long idUsuarioTelefone) {
		usuarioTelefoneRepository.deleteById(idUsuarioTelefone);
	}
	
	public List<UsuarioTelefone> findAll(Usuario usuario){
		return usuarioTelefoneRepository.findByUsuario(usuario);
	}
	
	public UsuarioTelefone save(UsuarioTelefone usuarioTelefone) {
		Telefone telefone = telefoneService.save(usuarioTelefone.getTelefone());
		usuarioTelefone.setTelefone(telefone);
		return usuarioTelefoneRepository.save(usuarioTelefone);
	}
	
	public List<UsuarioTelefone> save(List<UsuarioTelefone> list) {
		List<UsuarioTelefone> result = new ArrayList<>();
		for(UsuarioTelefone ut : list) {
			Telefone telefone = telefoneService.save(ut.getTelefone());
			ut.setTelefone(telefone);
			result.add(usuarioTelefoneRepository.save(ut));
		}
		
		return result;
	}
	

}
