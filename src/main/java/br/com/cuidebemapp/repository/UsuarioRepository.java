package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	public List<Usuario> findByEnabled(boolean enabled);
	
	@Query("select u from Usuario u left join fetch u.usuarioPhoto where u.login = :login")
	public Usuario findByLogin(@Param("login") String login);
	
}
