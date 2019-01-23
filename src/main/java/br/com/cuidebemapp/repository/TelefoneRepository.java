package br.com.cuidebemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

	
}
