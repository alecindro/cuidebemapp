package br.com.cuidebemapp.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cuidebemapp.model.PacientePhoto;
import br.com.cuidebemapp.service.PacientePhotoService;

@RestController
@RequestMapping("/api")
public class PacientePhotoResource {

	private final Logger log = LoggerFactory.getLogger(PacientePhotoResource.class);
	private final PacientePhotoService pacientePhotoService;
	
	public PacientePhotoResource(PacientePhotoService pacientePhotoService) {
		this.pacientePhotoService = pacientePhotoService;
	}
	
	@GetMapping("/pacientephotos/noprincipal")
    @Timed
    public List<PacientePhoto> getAllPacientePhotoNoPrincipal(@RequestParam("idPaciente") Long idPaciente){
		log.info("get photo no principal");
		return pacientePhotoService.findNoPrincipal(idPaciente);
	}
	
	@GetMapping("/pacientephotos/noprincipal/today")
    @Timed
    public List<PacientePhoto> getAllPacientePhotoNoPrincipalToday(@RequestParam("idPaciente") Long idPaciente){
		log.info("get photo no principal");
		return pacientePhotoService.findByPacientePhotoNotPrincipalToday(idPaciente);
	}
	
	@GetMapping("/pacientephotos/principal/{idPaciente}")
    @Timed
    public PacientePhoto getPacientePhotoPrincipal(@PathVariable("idPaciente")  Long idPaciente){
		log.info("get photo principal");
		return pacientePhotoService.findPrincipal(idPaciente);
	}
	
	@PostMapping("/pacientephotos/noprincipal")
    @Timed
    public PacientePhoto savePacientePhotoNoPrincipal(@RequestBody PacientePhoto pacientePhoto){
		log.info("save photo no principal");
		return pacientePhotoService.saveNoPrincipal(pacientePhoto);
	}
	
	@PostMapping("/pacientephotos/principal")
    @Timed
    public PacientePhoto savePacientePhotoPrincipal(@RequestBody PacientePhoto pacientePhoto){
		log.info("save photo principal");
		return pacientePhotoService.savePrincipal(pacientePhoto);
	}
	
	@PutMapping("/pacientephotos")
    @Timed
    public PacientePhoto updatePacientePhoto(@RequestBody PacientePhoto pacientePhoto){
		log.info("update photo principal");
		return pacientePhotoService.update(pacientePhoto);
	}
	
	@DeleteMapping("/pacientephotos/{idpacientephoto}")
    @Timed
    public void deletePacientePhoto(@PathVariable("idpacientephoto")  Long idpacientephoto){
		log.info("delete photo");
		pacientePhotoService.delete(idpacientephoto);
	}
}
