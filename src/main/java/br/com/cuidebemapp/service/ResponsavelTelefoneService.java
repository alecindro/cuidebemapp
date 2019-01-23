package br.com.cuidebemapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.model.ResponsavelTelefone;
import br.com.cuidebemapp.model.Telefone;
import br.com.cuidebemapp.repository.ResponsavelTelefoneRepository;

@Service
@Transactional
public class ResponsavelTelefoneService {

	@Autowired
	private ResponsavelTelefoneRepository responsavelTelefoneRepository;
	@Autowired
	private TelefoneService telefoneService;

	public Set<ResponsavelTelefone> save(Set<ResponsavelTelefone> responsavelTelefones, Responsavel responsavel) {

		if (!responsavelTelefones.isEmpty()) {
			responsavelTelefoneRepository.deleteByResponsavel(responsavel);
			for (ResponsavelTelefone rp : responsavelTelefones) {
				rp.setResponsavel(responsavel);
			}
			return new HashSet<ResponsavelTelefone>(responsavelTelefoneRepository.saveAll(responsavelTelefones));
		}
		return responsavelTelefones;
	}

	public ResponsavelTelefone save(ResponsavelTelefone responsavelTelefone, Responsavel responsavel) {

		responsavelTelefoneRepository.deleteByResponsavel(responsavel);
		responsavelTelefone.setResponsavel(responsavel);
		return responsavelTelefoneRepository.save(responsavelTelefone);
	}
	
	public ResponsavelTelefone save(ResponsavelTelefone responsavelTelefone) {
		Telefone telefone = telefoneService.save(responsavelTelefone.getTelefone());
		responsavelTelefone.setTelefone(telefone);
		return responsavelTelefoneRepository.save(responsavelTelefone);
	}
	
	public List<ResponsavelTelefone> save(List<ResponsavelTelefone> responsavelTelefoneList) {
		for(ResponsavelTelefone responsavelTelefone : responsavelTelefoneList) {
			Telefone telefone = telefoneService.save(responsavelTelefone.getTelefone());
			responsavelTelefone.setTelefone(telefone);
		}
		return responsavelTelefoneRepository.saveAll(responsavelTelefoneList);
	}
	
	public void delete(Long idresponsavelTelefone) {
		responsavelTelefoneRepository.deleteById(idresponsavelTelefone);
	}
	
	public List<ResponsavelTelefone> find(Responsavel responsavel){
		return responsavelTelefoneRepository.findByResponsavel(responsavel);
	}
}
