package br.com.cuidebemapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.Agenda;
import br.com.cuidebemapp.model.Agendadef;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.repository.AgendaRepository;
import br.com.cuidebemapp.service.util.DateUtil;

@Transactional
@Service
public class AgendaService {

	private final AgendaRepository repository;
	private static final Integer offset = 5;
	
	public AgendaService(AgendaRepository repository) {
		this.repository = repository;
	}
	
	public void save(Agendadef agendadef) {
		if(agendadef.getDiaspersonalizado()) {
			saveDiasPersonalizado(agendadef);
			return;
		}
		if(agendadef.getRepetirHoras() != null) {
			saveRepetirHoras(agendadef);
			return;
		}
		
		List<java.time.OffsetDateTime> dates = DateUtil.listDates(agendadef.getDatainicio(), agendadef.getDatafim(),
				agendadef.getHorario());
		save(getAgendas(agendadef, dates));
	}
	
	public void edit(Agendadef agendadef){
		delete(agendadef);
		save(agendadef);
	}
	
	public void setEvento(Agenda agenda, Long idevento) {
		agenda = repository.findById(agenda.getIdagenda()).get();
		agenda.setIdevento(idevento);
		repository.save(agenda);
	}
	
	public void delete(Agendadef agendadef){
		try {
		repository.deleteByAgendadefAndDataregistroIsNull(agendadef);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Page<Agenda> getAgendaByPaciente(Long idpaciente,Integer page){
		Sort sort = new Sort(Direction.DESC, "data");
		Pageable pageable = PageRequest.of(page, offset, sort);
		java.time.OffsetDateTime data = java.time.OffsetDateTime.now();
		data = data.plusHours(8L);
		return repository.findByPacienteAndDataBeforeAndIdeventoIsNullOrderByDataAsc(pageable, new Paciente(idpaciente), data);
	}
	
	private void saveDiasPersonalizado(Agendadef agendadef){
		List<java.time.OffsetDateTime> dates = DateUtil.listDates(agendadef.getDatainicio(), agendadef.getDatafim(), agendadef.getHorario(),
				agendadef.getDias());
		save(getAgendas(agendadef, dates));
	}
	
	private void saveRepetirHoras(Agendadef agendadef) {
		List<java.time.OffsetDateTime> dates = DateUtil.listDates(agendadef.getDatainicio(), agendadef.getDatafim(), agendadef.getHorario(),
				agendadef.getRepetirHoras());
		save(getAgendas(agendadef, dates));

	}

	
	private List<Agenda> getAgendas(Agendadef agendadef, List<java.time.OffsetDateTime> dates){
		List<Agenda> agendas = new ArrayList<Agenda>();
		for (java.time.OffsetDateTime date : dates) {
			Agenda agenda = new Agenda(agendadef);
			agenda.setData(date);
			agendas.add(agenda);
		}
		return agendas;
	}
	
	private void save(List<Agenda> agendas) {
		java.time.OffsetDateTime dataregistro = java.time.OffsetDateTime.now();
		for(Agenda agenda : agendas) {
			agenda.setDataregistro(dataregistro);
		}
		repository.saveAll(agendas);
	}
	
	public List<Agenda> findByIdPaciente(Integer idpaciente, java.time.OffsetDateTime dataInicio, java.time.OffsetDateTime dataFim){
		return repository.findByPacienteDataInicioFim(idpaciente, dataInicio, dataFim);
	}

}
