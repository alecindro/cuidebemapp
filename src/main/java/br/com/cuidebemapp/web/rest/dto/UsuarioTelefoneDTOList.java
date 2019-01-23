package br.com.cuidebemapp.web.rest.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebemapp.model.ResponsavelTelefone;
import br.com.cuidebemapp.model.UsuarioTelefone;

public class UsuarioTelefoneDTOList {
	
	private List<UsuarioTelefone> usuarioTelefones;

	
	
	public List<UsuarioTelefone> getUsuarioTelefones() {
		return usuarioTelefones;
	}

	public void setUsuarioTelefones(List<UsuarioTelefone> usuarioTelefones) {
		this.usuarioTelefones = usuarioTelefones;
	}

	public List<UsuarioTelefone> getAll(){
		List<UsuarioTelefone> list = new ArrayList<>();
		for(UsuarioTelefone usuarioTelefone : getUsuarioTelefones()) {
			list.add(usuarioTelefone);
		}
		return list;
	}
	
	public void getAll(List<UsuarioTelefone> list){
		usuarioTelefones = new ArrayList<>();
		for(UsuarioTelefone usuarioTelefone : list) {
			usuarioTelefones.add(usuarioTelefone);
		}
	}

}
