package br.com.cuidebemapp.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.cuidebemapp.enums.Check;
import br.com.cuidebemapp.model.Agenda;
import br.com.cuidebemapp.model.Evento;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.PacientePhoto;
import br.com.cuidebemapp.model.PatologiaPaciente;
import br.com.cuidebemapp.repository.AgendaRepository;
import br.com.cuidebemapp.repository.PacientePhotoRepository;
import br.com.cuidebemapp.repository.PatologiaPacienteRepository;
import br.com.cuidebemapp.service.dto.PacienteDTO;
import br.com.cuidebemapp.service.dto.PatologiaPacienteDTO;

@Service
@Transactional
public class PacienteDTOService {

	@Autowired
	private PacientePhotoRepository pacientePhotoRepository;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private AgendaRepository agendaRepository;
	@Autowired
	private PatologiaPacienteRepository patologiaPacienteRepository;

	public List<PacienteDTO> getPacienteDisabled() {
		List<Paciente> pacientes = pacienteService.findAllByEnabled(false);
		List<PacienteDTO> pacienteDTOs = new ArrayList<PacienteDTO>();
		for (Paciente paciente : pacientes) {
			String photo = getPhoto(paciente.getIdpaciente());
			Agenda agenda = nextAgenda(paciente.getIdpaciente());
			boolean checkin = isCheckin(paciente.getIdpaciente());
			PacienteDTO PacienteDTO = new PacienteDTO(paciente, photo, checkin, agenda);
			pacienteDTOs.add(PacienteDTO);
		}
		return pacienteDTOs;
	}

	public List<PacienteDTO> getPacienteEnabled() {
		List<Paciente> pacientes = pacienteService.findAllByEnabled(true);
		return getPacienteDTO(pacientes);
	}

	public List<PacienteDTO> getPacientePatologiaEnabled() {
		List<Paciente> pacientes = pacienteService.findByEnabled(true);
		return getPacienteDTO(pacientes);
	}

	private List<PacienteDTO> getPacienteDTO(List<Paciente> pacientes) {
		List<PacienteDTO> pacienteDTOs = new ArrayList<PacienteDTO>();
		for (Paciente paciente : pacientes) {
			String photo = getPhoto(paciente.getIdpaciente());
			Agenda agenda = nextAgenda(paciente.getIdpaciente());
			boolean checkin = isCheckin(paciente.getIdpaciente());
			PacienteDTO PacienteDTO = new PacienteDTO(paciente, photo, checkin, agenda);
			pacienteDTOs.add(PacienteDTO);
		}
		return pacienteDTOs;
	}

	private String getPhoto(Long idpaciente) {
		PacientePhoto pacientePhoto = pacientePhotoRepository.findPhotoPrincipal(idpaciente);
		if (pacientePhoto != null) {
			return pacientePhoto.getPhoto();
		}
		return null;
	}

	private Agenda nextAgenda(Long idpaciente) {
		return agendaRepository.findNexAgenda(idpaciente);
	}

	private boolean isCheckin(Long idpaciente) {
		String grupoevento = eventoService.getMaxEventoByIdPaciente(idpaciente);
		if (grupoevento == null || grupoevento.equals(Check.CHECKOUT.getDescricao())) {
			return false;
		}
		return true;
	}

	public PacienteDTO check(PacienteDTO pacienteDTO, boolean checkin) {
		Evento evento = eventoService.check(pacienteDTO.getPaciente(), checkin);
		pacienteDTO.getPaciente().getEventoSet().add(evento);
		pacienteDTO.setCheckin(checkin);
		return pacienteDTO;
	}

	public PacienteDTO save(PacienteDTO pacienteDTO, String login) {
		pacienteDTO.getPaciente().setEnabled(true);
		Paciente paciente = pacienteService.save(pacienteDTO.getPaciente());
		pacienteDTO.setPaciente(paciente);
		savePhoto(pacienteDTO, login);
		savePatologia(pacienteDTO);
		return pacienteDTO;
	}

	private void savePhoto(PacienteDTO pacienteDTO, String login) {
		if (!StringUtils.isEmpty(pacienteDTO.getPhoto())) {
			PacientePhoto pacientePhoto = new PacientePhoto();
			pacientePhoto.setPhoto(pacienteDTO.getPhoto());
			pacientePhoto.setDataregistro(OffsetDateTime.now());
			pacientePhoto.setPrincipal(true);
			pacientePhoto.setLogin(login);
			pacientePhoto.setPaciente(pacienteDTO.getPaciente());
			pacientePhoto = pacientePhotoRepository.save(pacientePhoto);

		}
	}
	
	private void updatePhotoPrincipal(PacienteDTO pacienteDTO, String login) {
		if (!StringUtils.isEmpty(pacienteDTO.getPhoto())) {
			PacientePhoto pp = pacientePhotoRepository.findPhotoPrincipal(pacienteDTO.getPaciente().getIdpaciente());
			if(pp == null) {
				pp = new PacientePhoto();
			}
			pp.setPaciente(pacienteDTO.getPaciente());
			pp.setPhoto(pacienteDTO.getPhoto());
			pp.setLogin(login);
			pp.setPrincipal(true);
			pacientePhotoRepository.save(pp);
		}
	}
	
	private void updatePatologia(PacienteDTO pacienteDTO) {
		List<PatologiaPaciente> salvar = new ArrayList<>();
		List<PatologiaPaciente> excluir = new ArrayList<>();
		for(PatologiaPacienteDTO pps : pacienteDTO.getPatologiasDTO()) {
			if(pps.isSelected() && pps.getPatologiaPaciente().getIdpatologiapaciente() == null) {
				PatologiaPaciente pp = pps.getPatologiaPaciente();
				pp.setPaciente(pacienteDTO.getPaciente());
				salvar.add(pp);
			}
			if(!pps.isSelected() && pps.getPatologiaPaciente().getIdpatologiapaciente() != null) {
				excluir.add(pps.getPatologiaPaciente());
			}
		}
		patologiaPacienteRepository.saveAll(salvar);
		patologiaPacienteRepository.deleteAll(excluir);
		
	}
	
	private void savePatologia(PacienteDTO pacienteDTO) {
		List<PatologiaPaciente> patologias = getPatologias(pacienteDTO);
		if (!patologias.isEmpty()) {
			for (PatologiaPaciente pp : patologias) {
				pp.setPaciente(pacienteDTO.getPaciente());
			}
			patologias = patologiaPacienteRepository.saveAll(patologias);
			setPatologias(patologias, pacienteDTO);
		}
	}

	public PacienteDTO update(PacienteDTO pacienteDTO,String login) {
		Paciente paciente = pacienteService.save(pacienteDTO.getPaciente());
		pacienteDTO.setPaciente(paciente);
		updatePatologia(pacienteDTO);
		updatePhotoPrincipal(pacienteDTO, login);
		return pacienteDTO;
	}
	
	private List<PatologiaPaciente> getPatologias(PacienteDTO pacienteDTO) {
		List<PatologiaPaciente> patologias = new ArrayList<>();
		for (PatologiaPacienteDTO pp : pacienteDTO.getPatologiasDTO()) {
			patologias.add(pp.getPatologiaPaciente());
		}
		return patologias;
	}
	
	private void setPatologias(List<PatologiaPaciente> patologias, PacienteDTO pacienteDTO) {
		pacienteDTO.setPatologiasDTO(new ArrayList<>());
		for (PatologiaPaciente pp : patologias) {
			PatologiaPacienteDTO dto = new PatologiaPacienteDTO(pp);
			pacienteDTO.getPatologiasDTO().add(dto);
		}

	}


}
