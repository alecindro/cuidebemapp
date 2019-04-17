package br.com.cuidebemapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cuidebemapp.model.Agendadef;
import br.com.cuidebemapp.model.Paciente;

public interface AgendadefRepository extends JpaRepository<Agendadef,Long> {

	String NEXT_AGENDA_CACHE = "nextAgenda";
	
	@Query(value = "select * from agenda where idagenda = (select min(idagenda) from agenda where date(data) = date(now()) and dataregistro is null and idpaciente = ?1)", nativeQuery = true)
   // @Cacheable(cacheNames = {NEXT_AGENDA_CACHE} , cacheResolver="dynCacheResolver")
	Agendadef findNextAgenda(Long idpaciente);
	
	@EntityGraph(attributePaths = {"paciente"})
	List<Agendadef> findByPacienteOrderByDatafimDesc(Paciente paciente);
	
	@EntityGraph(attributePaths = {"paciente"})
	Optional<Agendadef> findById(Long id);
}
