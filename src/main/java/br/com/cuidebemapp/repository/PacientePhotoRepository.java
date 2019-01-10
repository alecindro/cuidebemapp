package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.PacientePhoto;

@Repository
public interface PacientePhotoRepository extends JpaRepository<PacientePhoto, Long> {

	String PACIENTE_PHOTO = "pacientePhoto";
	String PACIENTE_PHOTO_ALL = "pacientePhotoAll";
	String PACIENTE_PHOTO_BY_USER = "pacientePhotoByUser";
	String PACIENTE_PHOTO_BY_PACIENTE = "pacientePhotoByPaciente";
	
//	@Cacheable(cacheNames = {PACIENTE_PHOTO_ALL}, cacheResolver="dynCacheResolver")
	public List<PacientePhoto> findAll();
	
//	@Cacheable(cacheNames = {PACIENTE_PHOTO_BY_USER}, cacheResolver="dynCacheResolver")
	public PacientePhoto findByLogin(String login);
	
//	@Cacheable(cacheNames = {PACIENTE_PHOTO_BY_PACIENTE}, cacheResolver="dynCacheResolver")
	public List<PacientePhoto> findByPacienteAndPrincipal(Paciente paciente, boolean principal);
	
	@Query(value="select pp from PacientePhoto pp where pp.paciente.idpaciente = ?1 and pp.principal = true")
//	@Cacheable(cacheNames = {PACIENTE_PHOTO}, cacheResolver="dynCacheResolver")
	public PacientePhoto findPhotoPrincipal(Long idpaciente);
	
	@EntityGraph(attributePaths = { "paciente" })
	List<PacientePhoto> findByPacienteAndPrincipalAndDataregistroBetween(Paciente paciente, boolean principal, java.time.OffsetDateTime init,
			java.time.OffsetDateTime end);
}
