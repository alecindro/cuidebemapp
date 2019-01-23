package br.com.cuidebemapp.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.model.ResponsavelTelefone;
import br.com.cuidebemapp.model.Telefone;

public class ResponsavelTelefoneDTO {

	  private Long idresponsavelTelefone;
	  private Responsavel responsavel;
	  private Telefone telefone;
	  @JsonIgnore
	  private ResponsavelTelefone responsavelTelefone;
	  
	  
	  public Long getIdresponsavelTelefone() {
		return idresponsavelTelefone;
	}
	public void setIdresponsavelTelefone(Long idresponsavelTelefone) {
		this.idresponsavelTelefone = idresponsavelTelefone;
	}
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public ResponsavelTelefone getResponsavelTelefone() {
		if(responsavelTelefone == null) {
			responsavelTelefone = new ResponsavelTelefone();
			responsavelTelefone.setResponsavel(responsavel);
			responsavelTelefone.setIdresponsavelTelefone(idresponsavelTelefone);
			responsavelTelefone.setTelefone(telefone);
		}
		return responsavelTelefone;
	}
	public void setResponsavelTelefone(ResponsavelTelefone responsavelTelefone) {
		this.idresponsavelTelefone = responsavelTelefone.getIdresponsavelTelefone();
		this.telefone = responsavelTelefone.getTelefone();
		this.responsavel = responsavelTelefone.getResponsavel();
		this.responsavelTelefone = responsavelTelefone;
	}
	
	
	
	
}
