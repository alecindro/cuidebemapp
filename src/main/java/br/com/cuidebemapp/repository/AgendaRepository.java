package br.com.cuidebemapp.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Agenda;
import br.com.cuidebemapp.model.Agendadef;	

@Repository
public interface AgendaRepository extends JpaRepository<Agenda,Long> {

	String NEXT_AGENDA_CACHE = "nextAgenda";
	
	@Query(value = "select * from agenda where idagenda = (select min(idagenda) from agenda where date(data) = date(now()) and dataregistro is null and idpaciente = ?1)", nativeQuery = true)
   // @Cacheable(cacheNames = {NEXT_AGENDA_CACHE} , cacheResolver="dynCacheResolver")
    Agenda findNextAgenda(Long idpaciente);
	
	@Modifying
	@Query(value = "delete from agenda where dataregistro is null and idagendadef = ?1 )", nativeQuery = true)
	void deleteByIdAgendaDef(Long idagendadef);
	
	void deleteByAgendadefAndDataregistroIsNull(Agendadef agendadef);
	
	@Query(value="SELECT a FROM Agenda a WHERE a.paciente.idpaciente = ?1 and a.data between ?2 and ?3 order by a.data asc")
	List<Agenda> findByPacienteDataInicioFim(Integer idPaciente, OffsetDateTime inicio, OffsetDateTime fim);
	
}
