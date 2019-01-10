package br.com.cuidebemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.PatologiaPaciente;

@Repository
public interface PatologiaPacienteRepository extends JpaRepository<PatologiaPaciente,Long>{

}
