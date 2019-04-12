package br.com.cuidebemapp.web.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.Evento;
import br.com.cuidebemapp.model.Memorando;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.PacientePhoto;
import br.com.cuidebemapp.service.EventoService;
import br.com.cuidebemapp.service.MemorandoService;
import br.com.cuidebemapp.service.PacientePhotoService;
import br.com.cuidebemapp.web.rest.dto.Timeline;

@RestController
@RequestMapping("/api")
public class TimelineResource {

	private final EventoService eventoService;
	private final PacientePhotoService pacientePhotoService;
	private final MemorandoService memorandoService;
	private static final Integer offset = 10;

	public TimelineResource(EventoService eventoService, PacientePhotoService pacientePhotoService,
			MemorandoService memorandoService) {
		this.eventoService = eventoService;
		this.pacientePhotoService = pacientePhotoService;
		this.memorandoService = memorandoService;
	}
	
	@GetMapping("/timelines")
	@Timed
	public List<Timeline> getTimelines(@RequestParam("idpaciente") Long idpaciente, @RequestParam("page") Integer page){
		Paciente paciente = new Paciente(idpaciente);
		ArrayList<Timeline> timelines = new ArrayList<Timeline>();
		loadEventosEnabled(timelines, paciente,page);
		loadMemos(timelines, paciente,page);
		loadPhotos(timelines, paciente, page);
		Collections.sort(timelines, new Timeline());
		return timelines;
		
		
	}

	private void loadEventosEnabled(List<Timeline> timelines, Paciente paciente, Integer page) {
		Sort sort = new Sort(Direction.DESC, "dataregistro");
		Pageable pageable = PageRequest.of(page, offset, sort);
		Page<Evento> eventos = eventoService.findEventoByPacientePageabe(paciente, pageable,true);
		for (Evento evento : eventos) {
			Timeline timeline = new Timeline();
			timeline.setTimelineData(evento,Timeline.TIPO_EVENTO);
			timeline.setDataEvento(evento.getDataregistro());
			timelines.add(timeline);
		}
	}

	private void loadMemos(List<Timeline> timelines, Paciente paciente,Integer page) {
		Sort sort = new Sort(Direction.DESC, "dataregistro");
		Pageable pageable = PageRequest.of(page, offset, sort);
		Page<Memorando> memorandos = memorandoService.getMemorandos(pageable, paciente);
		for (Memorando memorando : memorandos) {
			Timeline timeline = new Timeline();
			timeline.setTimelineData(memorando,Timeline.TIPO_MEMO);
			timeline.setDataEvento(memorando.getDataregistro());
			if(memorando.getDataalteracao() != null) {
			timeline.setDataEvento(memorando.getDataalteracao());
			}
			timelines.add(timeline);
		}
	}

	private void loadPhotos(List<Timeline> timelines, Paciente paciente, Integer page) {
		Sort sort = new Sort(Direction.DESC, "dataregistro");
		Pageable pageable = PageRequest.of(page, offset, sort);
		Page<PacientePhoto> photos = pacientePhotoService.findByPaciente(pageable, paciente);
		for (PacientePhoto photo : photos) {
			Timeline timeline = new Timeline();
			timeline.setTimelineData(photo, Timeline.TIPO_PHOTO);
			timeline.setDataEvento(photo.getDataregistro());
			timelines.add(timeline);
		}
	}
}
