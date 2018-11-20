package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long>{
		
	String PACIENTES_ENABLED_CACHE = "pacientesEnabled";

	@Cacheable(cacheNames= {PACIENTES_ENABLED_CACHE}, cacheResolver="dynCacheResolver")
	List<Paciente> findAllByEnabled(boolean enabled);

}
