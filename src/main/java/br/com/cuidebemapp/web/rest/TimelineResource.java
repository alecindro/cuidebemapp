package br.com.cuidebemapp.web.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	public TimelineResource(EventoService eventoService, PacientePhotoService pacientePhotoService,
			MemorandoService memorandoService) {
		this.eventoService = eventoService;
		this.pacientePhotoService = pacientePhotoService;
		this.memorandoService = memorandoService;
	}
	
	@GetMapping("/timeline")
	@Timed
	public List<Timeline> getTimelines(@RequestParam("idpaciente") Long idpaciente){
		Paciente paciente = new Paciente(idpaciente);
		ArrayList<Timeline> timelines = new ArrayList<Timeline>();
		loadEventos(timelines, paciente);
		loadMemos(timelines, paciente);
		loadPhotos(timelines, paciente);
		Collections.sort(timelines, new Timeline());
		return timelines;
		
		
	}

	private void loadEventos(List<Timeline> timelines, Paciente paciente) {
		List<Evento> eventos = eventoService.getEventosEnabledToday(paciente);
		for (Evento evento : eventos) {
			Timeline timeline = new Timeline();
			timeline.setTimelineData(evento,Timeline.TIPO_EVENTO);
			timeline.setDataEvento(evento.getDataevento());
			timelines.add(timeline);
		}
	}

	private void loadMemos(List<Timeline> timelines, Paciente paciente) {
		List<Memorando> memorandos = memorandoService.getMemorandosToday(paciente);
		for (Memorando memorando : memorandos) {
			Timeline timeline = new Timeline();
			timeline.setTimelineData(memorando,Timeline.TIPO_MEMO);
			timeline.setDataEvento(memorando.getDataalteracao());
			timelines.add(timeline);
		}
	}

	private void loadPhotos(List<Timeline> timelines, Paciente paciente) {
		List<PacientePhoto> photos = pacientePhotoService.findByPacientePhotoNotPrincipalToday(paciente.getIdpaciente());
		for (PacientePhoto photo : photos) {
			Timeline timeline = new Timeline();
			timeline.setTimelineData(photo, Timeline.TIPO_PHOTO);
			timeline.setDataEvento(photo.getDataregistro());
			timelines.add(timeline);
		}
	}
}
