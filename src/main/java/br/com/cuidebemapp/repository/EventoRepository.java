package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Evento;
import br.com.cuidebemapp.model.Paciente;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {

	String MAX_EVENTO = "maxEvento";
	String EVENTO_PACIENTE = "eventoPaciente";
	String EVENTO_TIMELINE = "eventoTimeline";
	String EVENTO_PACIENTE_TOP30 = "eventoPacienteTop30";
	
	@Query(value = "select grupoEvento from evento where idevento = (select max(idevento) from evento where idpaciente = ?1)", nativeQuery = true)
   // @Cacheable(cacheNames = {MAX_EVENTO} , cacheResolver="dynCacheResolver")
	String getMaxEventoByIdPaciente(Long idpaciente);

	
/*	@Query(value = "select * from evento where idpaciente = ?1 and date(dataevento) = date( ?2 ) order by dataevento desc", nativeQuery = true)
    @Cacheable(cacheNames = EVENTO_PACIENTE)
	List<Evento> getEventoTodayByIdPaciente(Long idpaciente, Date data);
	*/
	
	@EntityGraph(attributePaths = {"usuario"})
    Page<Evento> findEventoByPacienteAndEnabled(Pageable pageable,Paciente paciente, boolean enabled);
	
	@EntityGraph(attributePaths = {"usuario"})
	List<Evento> findTop30ByPacienteOrderByDataregistroDesc(Paciente paciente);
	
	@EntityGraph(attributePaths = { "paciente","usuario" })
	List<Evento> findByPacienteAndDataregistroBetween(Paciente paciente, java.time.OffsetDateTime init,
			java.time.OffsetDateTime end);
	
	@EntityGraph(attributePaths = { "paciente","usuario" })
	List<Evento> findByPacienteAndDataregistroBetweenAndEnabled(Paciente paciente, java.time.OffsetDateTime init,
			java.time.OffsetDateTime end, boolean enabled);

}
