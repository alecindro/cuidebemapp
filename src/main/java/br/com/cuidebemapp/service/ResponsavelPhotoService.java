package br.com.cuidebemapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.ResponsavelPhoto;
import br.com.cuidebemapp.repository.ResponsavelPhotoRepository;

@Service
@Transactional
public class ResponsavelPhotoService {

	@Autowired
	private ResponsavelPhotoRepository responsavelPhotoRepository;
	
	public ResponsavelPhoto save(ResponsavelPhoto responsavelPhoto) {
		if(responsavelPhoto.getIdresponsavel() == null) {
			responsavelPhoto.setIdresponsavel(responsavelPhoto.getResponsavel().getIdresponsavel());
		}
		ResponsavelPhoto rp =  responsavelPhotoRepository.save(responsavelPhoto);
		return rp;
	}
	
	public void delete(ResponsavelPhoto responsavelPhoto) {
		responsavelPhotoRepository.delete(responsavelPhoto);
	}
	
	public Optional<ResponsavelPhoto> findByResponsavel(Long id) {
		return responsavelPhotoRepository.findById(id);
	}
	
}
