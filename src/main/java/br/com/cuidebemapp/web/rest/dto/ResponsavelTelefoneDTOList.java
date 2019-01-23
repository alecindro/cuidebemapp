package br.com.cuidebemapp.web.rest.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebemapp.model.ResponsavelTelefone;

public class ResponsavelTelefoneDTOList {

	private List<ResponsavelTelefoneDTO> responsavelTelefones;
		


	public List<ResponsavelTelefoneDTO> getResponsavelTelefones() {
		return responsavelTelefones;
	}

	public void setResponsavelTelefones(List<ResponsavelTelefoneDTO> responsavelTelefones) {
		this.responsavelTelefones = responsavelTelefones;
	}

	public List<ResponsavelTelefone> getAll(){
		List<ResponsavelTelefone> list = new ArrayList<>();
		for(ResponsavelTelefoneDTO responsavelTelefoneDTO : getResponsavelTelefones()) {
			list.add(responsavelTelefoneDTO.getResponsavelTelefone());
		}
		return list;
	}
	
	public void getAll(List<ResponsavelTelefone> list){
		responsavelTelefones = new ArrayList<>();
		for(ResponsavelTelefone responsavelTelefone : list) {
			ResponsavelTelefoneDTO dto = new ResponsavelTelefoneDTO();
			dto.setResponsavelTelefone(responsavelTelefone);
			responsavelTelefones.add(dto);
		}
	}
	
	
	
	
	
	
}
