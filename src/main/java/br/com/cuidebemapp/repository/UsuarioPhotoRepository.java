package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.UsuarioPhoto;

@Repository
public interface UsuarioPhotoRepository extends JpaRepository<UsuarioPhoto, Long> {


	@Query("select up from UsuarioPhoto up join fetch up.usuario u where u.enabled = true ")
	public List<UsuarioPhoto> findUsuarioPhotoEnabled();
	
}
