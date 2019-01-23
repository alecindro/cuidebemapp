package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>{

	
	public List<Responsavel> findAll();
	
	@Query("select distinct resp from Responsavel resp join resp.responsavelPacientes rps join rps.paciente pac left join fetch resp.responsavelTelefones rt left join fetch rt.telefone tel left join fetch resp.responsavelPhoto where pac.idpaciente = :idpaciente ")
	public List<Responsavel> findByPaciente(@Param("idpaciente") Long idpaciente);
}
