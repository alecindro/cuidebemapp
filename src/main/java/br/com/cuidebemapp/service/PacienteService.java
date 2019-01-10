package br.com.cuidebemapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.repository.PacienteRepository;

@Service
@Transactional
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	

	
	public Paciente delete(Long idpaciente) {
		Paciente paciente = pacienteRepository.findById(idpaciente).get();
		paciente.setEnabled(false);
		return pacienteRepository.save(paciente);
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
	
	public List<Paciente> findByEnabled(boolean enabled){
		return pacienteRepository.findByEnabled(enabled);
	}
	
	public List<Paciente> findAllByEnabled(boolean enabled){
		return pacienteRepository.findAllByEnabled(enabled);
	}

}
