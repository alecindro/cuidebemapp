package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.model.ResponsavelPaciente;

@Repository
public interface ResponsavelPacienteRepository extends JpaRepository<ResponsavelPaciente, Long>{

	//@EntityGraph(attributePaths = {"responsavel","paciente"})
	@Query("select rp from ResponsavelPaciente rp join fetch rp.paciente left join fetch rp.responsavel r left join fetch r.responsavelPhoto where rp.paciente.idpaciente = :idpaciente ")
	public List<ResponsavelPaciente> findByPaciente(@Param("idpaciente") Long idpaciente);
	
	public ResponsavelPaciente findByPacienteAndResponsavel(Paciente paciente,Responsavel responsavel);
}
