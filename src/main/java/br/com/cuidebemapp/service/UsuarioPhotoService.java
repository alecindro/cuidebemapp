package br.com.cuidebemapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.UsuarioPhoto;
import br.com.cuidebemapp.repository.UsuarioPhotoRepository;

@Service
@Transactional
public class UsuarioPhotoService {

	@Autowired
	private UsuarioPhotoRepository usuarioPhotoRepository;
	
	public UsuarioPhoto save(UsuarioPhoto usuarioPhoto) {
		if(usuarioPhoto.getLogin() == null) {
			usuarioPhoto.setLogin(usuarioPhoto.getUsuario().getLogin());
		}
		UsuarioPhoto rp =  usuarioPhotoRepository.save(usuarioPhoto);
		return rp;
	}
	
	public List<UsuarioPhoto> findEnabled(){
		return usuarioPhotoRepository.findUsuarioPhotoEnabled();
	}
	
	
	
	
	
}
