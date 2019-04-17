package br.com.cuidebemapp.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.Agendadef;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.repository.AgendadefRepository;

@Transactional
@Service
public class AgendadefService {

	
private final AgendadefRepository repository;
private final AgendaService agendaService;
	
	public AgendadefService(AgendadefRepository repository, AgendaService agendaService) {
		this.repository = repository;
		this.agendaService = agendaService;
	}
	
	public Agendadef save(Agendadef agendadef) {
		agendadef.setDataRegistro(OffsetDateTime.now());
		agendadef.setEnabled(true);
		agendadef = this.repository.save(agendadef);
		this.agendaService.save(agendadef);
		return agendadef;
	}
	
	public List<Agendadef> getAgendaDefByPaciente(Long idpaciente){
		return repository.findByPacienteOrderByDatafimDesc(new Paciente(idpaciente));
	}
	
	public Optional<Agendadef> findAgenda(Long idagendadef){
		return repository.findById(idagendadef);
	}
	
	public Agendadef update(Agendadef agendadef) {
		agendadef.setDataRegistro(OffsetDateTime.now());
		agendadef = this.repository.save(agendadef);
		this.agendaService.edit(agendadef);
		return agendadef;
	}
	
	public void delete(Long idagendadef) {
		this.agendaService.delete(new Agendadef(idagendadef));
		this.repository.deleteById(idagendadef);
		
	}
	
	
	
	
}
