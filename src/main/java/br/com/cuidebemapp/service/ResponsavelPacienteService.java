package br.com.cuidebemapp.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.model.ResponsavelPaciente;
import br.com.cuidebemapp.model.ResponsavelPhoto;
import br.com.cuidebemapp.model.ResponsavelTelefone;
import br.com.cuidebemapp.repository.ResponsavelPacienteRepository;

@Service
@Transactional
public class ResponsavelPacienteService {

	@Autowired
	private ResponsavelPacienteRepository responsavelPacienteRepository;
	@Autowired
	private ResponsavelService responsavelService;
	@Autowired
	private ResponsavelPhotoService responsavelPhotoService;
	@Autowired
	private ResponsavelTelefoneService responsavelTelefoneService;


	public List<ResponsavelPaciente> findByPaciente(Long idpaciente) {
		List<ResponsavelPaciente> rps = responsavelPacienteRepository.findByPaciente(idpaciente);
		return rps;
	}
	
	public ResponsavelPaciente findByPacienteandResponsvel(Long idpaciente, Long idresponsavel) {
		return responsavelPacienteRepository.findByPacienteAndResponsavel(new Paciente(idpaciente), new Responsavel(idresponsavel));
	}

	public ResponsavelPaciente save(ResponsavelPaciente responsavelPaciente) {
		ResponsavelPhoto responsavelPhoto = responsavelPaciente.getResponsavel().getResponsavelPhoto();
		Set<ResponsavelTelefone> telefones = responsavelPaciente.getResponsavel().getResponsavelTelefones();
		responsavelPaciente.setResponsavel(responsavelService.save(responsavelPaciente.getResponsavel()));
		if (responsavelPhoto != null) {
			responsavelPhoto.setIdresponsavel(responsavelPaciente.getResponsavel().getIdresponsavel());
			responsavelPhoto = responsavelPhotoService.save(responsavelPhoto);
			responsavelPaciente.getResponsavel().setResponsavelPhoto(responsavelPhoto);
		}
	/*	if (telefones != null) {
			responsavelTelefoneService.save(telefones, responsavelPaciente.getResponsavel());
		}*/
		responsavelPaciente = responsavelPacienteRepository.save(responsavelPaciente);
		return responsavelPaciente;
	}

	public void delete(ResponsavelPaciente responsavelPaciente) {
		responsavelPacienteRepository.delete(responsavelPaciente);
	}
}
