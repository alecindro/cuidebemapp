package br.com.cuidebemapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cuidebemapp.enums.Check;
import br.com.cuidebemapp.model.Agenda;
import br.com.cuidebemapp.model.Evento;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.PacientePhoto;
import br.com.cuidebemapp.repository.AgendaRepository;
import br.com.cuidebemapp.repository.PacientePhotoRepository;
import br.com.cuidebemapp.repository.PacienteRepository;
import br.com.cuidebemapp.service.dto.PacienteDTO;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private PacientePhotoRepository pacientePhotoRepository;
	@Autowired 
	private AgendaRepository agendaRepository;
	@Autowired
	private EventoService eventoService;
	
	
	public List<PacienteDTO> getPacienteEnabled(){
		List<Paciente> pacientes = pacienteRepository.findAllByEnabled(true);
		List<PacienteDTO> pacienteDTOs = new ArrayList<PacienteDTO>(); 
		for(Paciente paciente : pacientes) {
			String photo = getPhoto(paciente.getIdpaciente());
			Agenda agenda = nextAgenda(paciente.getIdpaciente());
			boolean checkin = isCheckin(paciente.getIdpaciente());
			PacienteDTO PacienteDTO = new PacienteDTO(paciente,photo,checkin,agenda);
			pacienteDTOs.add(PacienteDTO);
		}
		return pacienteDTOs;
	}
	
	public List<PacienteDTO> getPacienteDisabled(){
		List<Paciente> pacientes = pacienteRepository.findAllByEnabled(false);
		List<PacienteDTO> pacienteDTOs = new ArrayList<PacienteDTO>(); 
		for(Paciente paciente : pacientes) {
			String photo = getPhoto(paciente.getIdpaciente());
			Agenda agenda = nextAgenda(paciente.getIdpaciente());
			boolean checkin = isCheckin(paciente.getIdpaciente());
			PacienteDTO PacienteDTO = new PacienteDTO(paciente,photo,checkin,agenda);
			pacienteDTOs.add(PacienteDTO);
		}
		return pacienteDTOs;
	}
	
	public Paciente delete(Long idpaciente) {
		Paciente paciente = pacienteRepository.findById(idpaciente).get();
		paciente.setEnabled(false);
		return pacienteRepository.save(paciente);
	}
	
	public String getPhoto(Long idpaciente) {
		PacientePhoto pacientePhoto = pacientePhotoRepository.findPhotoPrincipal(idpaciente);
		if(pacientePhoto!= null) {
			return pacientePhoto.getPhoto();
		}
		return null;
	}
	
	public Agenda nextAgenda(Long idpaciente) {
		return agendaRepository.findNexAgenda(idpaciente);
	}
	
	public boolean isCheckin(Long idpaciente) {
		String grupoevento = eventoService.getMaxEventoByIdPaciente(idpaciente);
		if(grupoevento == null || grupoevento.equals(Check.CHECKOUT.getDescricao())) {
			return false;
		}
		return true;
	}
	
	public Paciente save(Paciente paciente) {
		return pacienteRepository.save(paciente);
		
	}
	
	public Paciente getPaciente(Long id) {
		return pacienteRepository.findById(id).get();
		
	}
	
	public Paciente update(Paciente paciente) {
		return pacienteRepository.save(paciente);
		
	}
	
	public PacienteDTO check(PacienteDTO pacienteDTO, boolean checkin) {
		Evento evento = eventoService.check(pacienteDTO.getPaciente(), checkin);
		pacienteDTO.getPaciente().getEventoSet().add(evento);
		pacienteDTO.setCheckin(checkin);
		return pacienteDTO;
	}
}
