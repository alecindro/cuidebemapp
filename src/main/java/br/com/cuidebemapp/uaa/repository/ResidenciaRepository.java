package br.com.cuidebemapp.uaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.uaa.model.Residencia;	

@Repository
public interface ResidenciaRepository extends JpaRepository<Residencia,Long> {

	
}
