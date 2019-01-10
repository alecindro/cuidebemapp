package br.com.cuidebemapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.repository.ResponsavelRepository;

@Service
@Transactional
public class ResponsavelService {

	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	public Responsavel save(Responsavel responsavel) {
		responsavel.setEnabled(true);
		return responsavelRepository.save(responsavel);
	}
}
