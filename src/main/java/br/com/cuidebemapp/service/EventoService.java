package br.com.cuidebemapp.service;

import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.enums.Check;
import br.com.cuidebemapp.model.Agenda;
import br.com.cuidebemapp.model.Evento;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.repository.EventoRepository;
import br.com.cuidebemapp.security.SecurityUtils;
import br.com.cuidebemapp.util.DateUtil;

@Service
@Transactional
public class EventoService {
	
	private final CacheManager cacheManager;
	private final EventoRepository eventoRepository;
	private final AgendaService agendaService;
	
	
	public EventoService(CacheManager cacheManager, EventoRepository eventoRepository,AgendaService agendaService) {
		super();
		this.cacheManager = cacheManager;
		this.eventoRepository = eventoRepository;
		this.agendaService = agendaService;
	}
	
	public Evento save(Evento evento) {
		Agenda agenda = evento.getAgenda();
		evento.setUsuario(SecurityUtils.getCurrentUsuario());
		evento.setDataregistro(java.time.OffsetDateTime.now());
		//Paciente paciente = evento.getPaciente();
		evento =  eventoRepository.save(evento);
		if(agenda != null) {
			agendaService.setEvento(agenda, evento.getIdevento());
		}
	//	cacheManager.getCache(EventoRepository.EVENTO_PACIENTE_TOP30).evict(paciente);
	//	cacheManager.getCache(EventoRepository.MAX_EVENTO).evict(paciente.getIdpaciente());
        return evento;
	}
	
	public Evento update(Evento evento) {
		return eventoRepository.save(evento);
	}
	
	public Evento delete(Evento evento) {
		evento.setEnabled(false);
		return eventoRepository.save(evento);
	}
	
	public Page<Evento> findEventoByPacientePageabe(Paciente paciente, Pageable pageable, boolean enabled){
		return eventoRepository.findEventoByPacienteAndEnabled(pageable,paciente, enabled);
	}
	
	public List<Evento> findTop30ByPacienteOrderByDataregistroDesc(Paciente paciente) {
		return eventoRepository.findTop30ByPacienteOrderByDataregistroDesc(paciente);
	}
	
	public Evento check(Paciente paciente, boolean checkin) {
		Evento evento = new Evento();
		java.time.OffsetDateTime dataevento = DateUtil.dataAgora();
		if (checkin) {
			evento.setGrupoevento(Check.CHECKIN.getDescricao());
		} else {
			evento.setGrupoevento(Check.CHECKOUT.getDescricao());
		}
		evento.setPaciente(paciente);
		evento.setDataregistro(dataevento);
		evento.setDataevento(dataevento);
		evento.setEnabled(true);
		return save(evento);
	}
	
	public String getMaxEventoByIdPaciente(Long idpaciente) {
		return eventoRepository.getMaxEventoByIdPaciente(idpaciente);
	}
	
	public List<Evento> getEventosToday(Paciente paciente) {
		return eventoRepository.findByPacienteAndDataregistroBetween(paciente, DateUtil.dataAntes24Hs(),
				DateUtil.dataAgora());
	}
	public List<Evento> getEventosEnabledToday(Paciente paciente) {
		return eventoRepository.findByPacienteAndDataregistroBetweenAndEnabled(paciente, DateUtil.dataAntes24Hs(),
				DateUtil.dataAgora(),true);
	}

}
