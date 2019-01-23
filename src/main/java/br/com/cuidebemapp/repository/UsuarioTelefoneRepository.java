package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Usuario;
import br.com.cuidebemapp.model.UsuarioTelefone;

@Repository
public interface UsuarioTelefoneRepository extends JpaRepository<UsuarioTelefone, Long> {

	
@EntityGraph(attributePaths = "telefone")
public List<UsuarioTelefone> findByUsuario(Usuario usuario);
	
}
