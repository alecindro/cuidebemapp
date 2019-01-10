package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.ResponsavelPaciente;

@Repository
public interface ResponsavelPacienteRepository extends JpaRepository<ResponsavelPaciente, Long>{

	@EntityGraph(attributePaths = {"responsavel","paciente"})
	public List<ResponsavelPaciente> findByPaciente(Paciente paciente);
}
