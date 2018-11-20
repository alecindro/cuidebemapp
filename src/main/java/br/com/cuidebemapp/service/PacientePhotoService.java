package br.com.cuidebemapp.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.PacientePhoto;
import br.com.cuidebemapp.repository.PacientePhotoRepository;
import br.com.cuidebemapp.security.SecurityUtils;
import br.com.cuidebemapp.util.DateUtil;

@Service
public class PacientePhotoService {

	@Autowired
	private PacientePhotoRepository pacientePhotoRepository;

	public List<PacientePhoto> findByPacientePhotoNotPrincipalToday(Paciente paciente) {
		return pacientePhotoRepository.findByPacienteAndPrincipalAndDataregistroBetween(paciente, false,
				DateUtil.dataAntes24Hs(), DateUtil.dataAgora());
	}
	
	public List<PacientePhoto> findByPacientePhotoNotPrincipalToday(Long idPaciente) {
		return pacientePhotoRepository.findByPacienteAndPrincipalAndDataregistroBetween(new Paciente(idPaciente), false,
				DateUtil.dataAntes24Hs(), DateUtil.dataAgora());
	}
	
	private PacientePhoto save(PacientePhoto pacientePhoto,boolean principal) {
		pacientePhoto.setDataregistro(OffsetDateTime.now());
		pacientePhoto.setPrincipal(principal);
		pacientePhoto.setLogin(SecurityUtils.getCurrentUserLogin().get());
		return pacientePhotoRepository.save(pacientePhoto);
	}
	
	public PacientePhoto savePrincipal(PacientePhoto pacientePhoto) {
		PacientePhoto principal = findPrincipal(pacientePhoto.getPaciente().getIdpaciente());
		if(principal != null) {
			delete(principal.getIdpacientephoto());
		}
		return save(pacientePhoto,true);
	}
	
	public PacientePhoto saveNoPrincipal(PacientePhoto pacientePhoto) {
		return save(pacientePhoto,false);
	}

	public PacientePhoto update(PacientePhoto pacientePhoto) {
		return pacientePhotoRepository.save(pacientePhoto);
	}

	public void delete(Long id) {
		pacientePhotoRepository.deleteById(id);
	}
	
	public void delete(PacientePhoto pacientePhoto) {
		delete(pacientePhoto.getIdpacientephoto());
	}

	public List<PacientePhoto> findNoPrincipal(Long idPaciente) {
		Paciente paciente = new Paciente(idPaciente);
		return pacientePhotoRepository.findByPacienteAndPrincipal(paciente,false);
	}
	
	public PacientePhoto findPrincipal(Long idPaciente) {
		return pacientePhotoRepository.findPhotoPrincipal(idPaciente);
	}
	

}
