package br.com.cuidebemapp.service.dto;

import br.com.cuidebemapp.uaa.model.Residencia;

public class ResidenciaDTO {

	
	private Residencia residencia;
	private AdminDTO adminDTO;
	
	public Residencia getResidencia() {
		return residencia;
	}
	
	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}
	
	


	public AdminDTO getAdminDTO() {
		return adminDTO;
	}

	public void setAdminDTO(AdminDTO adminDTO) {
		this.adminDTO = adminDTO;
	}

	@Override
	public String toString() {
		return "ResidenciaDTO [residencia=" + ((residencia == null) ? "" : residencia.toString()) + ", userDTO=" +((adminDTO == null)? "" :  adminDTO.toString()) + "]";
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((residencia == null) ? 0 : residencia.hashCode());
		result = prime * result + ((adminDTO == null) ? 0 : adminDTO.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResidenciaDTO other = (ResidenciaDTO) obj;
		if (residencia == null) {
			if (other.residencia != null)
				return false;
		} else if (!residencia.equals(other.residencia))
			return false;
		if (adminDTO == null) {
			if (other.adminDTO != null)
				return false;
		} else if (!adminDTO.equals(other.adminDTO))
			return false;
		return true;
	}

	
	
	
	
}
