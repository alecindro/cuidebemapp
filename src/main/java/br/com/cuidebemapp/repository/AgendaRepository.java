package br.com.cuidebemapp.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Agenda;	

@Repository
public interface AgendaRepository extends JpaRepository<Agenda,Long> {

	String NEXT_AGENDA_CACHE = "nextAgenda";
	
	@Query(value = "select * from agenda where idagenda = (select min(idagenda) from agenda where date(data) = date(now()) and dataregistro is null and idpaciente = ?1)", nativeQuery = true)
    @Cacheable(cacheNames = {NEXT_AGENDA_CACHE} , cacheResolver="dynCacheResolver")
    Agenda findNexAgenda(Long idpaciente);
}
