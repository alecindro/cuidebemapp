package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>{

	
	public List<Responsavel> findAll();
}
