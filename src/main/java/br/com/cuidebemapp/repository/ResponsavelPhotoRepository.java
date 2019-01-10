package br.com.cuidebemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cuidebemapp.model.ResponsavelPhoto;

@Repository
public interface ResponsavelPhotoRepository extends JpaRepository<ResponsavelPhoto, Long> {

	public List<ResponsavelPhoto> findAll();

}
