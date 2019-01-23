package br.com.cuidebemapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.Telefone;
import br.com.cuidebemapp.repository.TelefoneRepository;

@Service
@Transactional
public class TelefoneService {

	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public List<Telefone> saveAll(List<Telefone> telefones) {
		return telefoneRepository.saveAll(telefones);
	}
	
	public Telefone save(Telefone telefone) {
		return telefoneRepository.save(telefone);
	}
}
