package br.com.cuidebemapp.service.dto;

import br.com.cuidebemapp.model.Agenda;
import br.com.cuidebemapp.model.Paciente;

public class PacienteDTO {

	private Paciente paciente;
	private String photo;
	private boolean checkin;
	private Agenda nextAgenda;
	
	
	public PacienteDTO() {
		super();
	}
	
	public PacienteDTO(Paciente paciente, String photo, boolean checkin, Agenda nextAgenda) {
		super();
		this.paciente = paciente;
		this.photo = photo;
		this.checkin = checkin;
		this.nextAgenda = nextAgenda;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public boolean isCheckin() {
		return checkin;
	}
	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}
	public Agenda getNextAgenda() {
		return nextAgenda;
	}
	public void setNextAgenda(Agenda nextAgenda) {
		this.nextAgenda = nextAgenda;
	}

	@Override
	public String toString() {
		return "PacienteDTO [paciente=" + paciente.toString() + ", photo=" + photo + ", checkin=" + checkin + ", nextAgenda="
				+ nextAgenda + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (checkin ? 1231 : 1237);
		result = prime * result + ((nextAgenda == null) ? 0 : nextAgenda.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
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
		PacienteDTO other = (PacienteDTO) obj;
		if (checkin != other.checkin)
			return false;
		if (nextAgenda == null) {
			if (other.nextAgenda != null)
				return false;
		} else if (!nextAgenda.equals(other.nextAgenda))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		return true;
	}
	
	
}
