package br.com.cuidebemapp.service.dto;

import br.com.cuidebemapp.model.PatologiaPaciente;

public class PatologiaPacienteDTO {
	
	private PatologiaPaciente patologiaPaciente;
	private boolean selected;
	
	public PatologiaPacienteDTO() {
		
	}
	
	public PatologiaPacienteDTO(PatologiaPaciente patologiaPaciente) {
		this.patologiaPaciente = patologiaPaciente;
		if(patologiaPaciente!= null && patologiaPaciente.getIdpatologiapaciente()!= null) {
			this.selected = true;
		}
	}
	
	public PatologiaPaciente getPatologiaPaciente() {
		return patologiaPaciente;
	}
	public void setPatologiaPaciente(PatologiaPaciente patologiaPaciente) {
		this.patologiaPaciente = patologiaPaciente;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	
	
	

}
