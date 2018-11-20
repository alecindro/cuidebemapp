package br.com.cuidebemapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Memorando;
import br.com.cuidebemapp.model.Paciente;

@Repository
public interface MemorandoRepository extends JpaRepository<Memorando, Long> {

	@EntityGraph(attributePaths = { "paciente" })
	List<Memorando> findByPacienteAndDataregistroBetween(Paciente paciente, java.time.OffsetDateTime init,
			java.time.OffsetDateTime end);

	List<Memorando> findByPaciente(Paciente paciente);
	
	@EntityGraph(attributePaths = { "paciente" })
	Optional<Memorando> findById(Long id);

}
