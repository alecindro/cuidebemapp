package br.com.cuidebemapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.ResponsavelPaciente;
import br.com.cuidebemapp.repository.ResponsavelPacienteRepository;

@Service
@Transactional
public class ResponsavelPacienteService {

	@Autowired
	private ResponsavelPacienteRepository responsavelPacienteRepository;

	public List<ResponsavelPaciente> findByPaciente(Paciente paciente) {
		List<ResponsavelPaciente> result = new ArrayList<>();
		List<ResponsavelPaciente> rps = responsavelPacienteRepository.findByPaciente(paciente);
		for(ResponsavelPaciente rp : rps) {
			if(rp.getResponsavel().getEnabled()) {
				result.add(rp);
			}
		}
		return result;
	}

	public List<ResponsavelPaciente> findByPaciente(Long idpaciente) {
		Paciente paciente = new Paciente(idpaciente);
		return findByPaciente(paciente);
	}

	public ResponsavelPaciente save(ResponsavelPaciente responsavelPaciente) {
		return responsavelPacienteRepository.save(responsavelPaciente);
	}
	
	public void delete(ResponsavelPaciente responsavelPaciente) {
		responsavelPacienteRepository.delete(responsavelPaciente);
	}
}

