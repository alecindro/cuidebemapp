package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	public List<Usuario> findByEnabled(boolean enabled);
	
}
