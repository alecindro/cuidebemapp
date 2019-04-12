package br.com.cuidebemapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@EntityGraph(attributePaths = { "usuario" })
	List<Memorando> findTop30ByPacienteOrderByDataregistroDesc(Paciente paciente);

	@EntityGraph(attributePaths = { "usuario" })
	Page<Memorando> findByPaciente(Pageable pageable,Paciente paciente);

}
