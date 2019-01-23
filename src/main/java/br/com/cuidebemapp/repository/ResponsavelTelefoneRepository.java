package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.model.ResponsavelTelefone;

@Repository
public interface ResponsavelTelefoneRepository extends JpaRepository<ResponsavelTelefone, Long> {

public void deleteByResponsavel(Responsavel responsavel);

public List<ResponsavelTelefone> findByResponsavel(Responsavel responsavel);
	
}
