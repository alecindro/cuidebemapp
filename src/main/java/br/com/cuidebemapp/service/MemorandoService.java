package br.com.cuidebemapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cuidebemapp.model.Memorando;
import br.com.cuidebemapp.model.Paciente;
import br.com.cuidebemapp.model.Usuario;
import br.com.cuidebemapp.repository.MemorandoRepository;
import br.com.cuidebemapp.security.SecurityUtils;
import br.com.cuidebemapp.util.DateUtil;

@Service
public class MemorandoService {

	@Autowired
	private MemorandoRepository memorandoRepository;

	public Memorando save(Memorando memorando) {
		memorando.setDataregistro(DateUtil.dataAgora());
		memorando.setUsuario(new Usuario(SecurityUtils.getCurrentUserLogin().get()));
		return memorandoRepository.save(memorando);
	}

	public Memorando update(Memorando memorando) {
		return memorandoRepository.save(memorando);
	}

	public void delete(Long idmemorando) {
		memorandoRepository.deleteById(idmemorando);
	}

	public List<Memorando> getMemorandosToday(Paciente paciente) {
		return getMemorandos(paciente, DateUtil.dataAntes24Hs(),
				DateUtil.dataAgora());
	}

	public List<Memorando> getMemorandos(Paciente paciente, java.time.OffsetDateTime init, java.time.OffsetDateTime end) {
		return memorandoRepository.findByPacienteAndDataregistroBetween(paciente,init, end);
	}

	public List<Memorando> getMemorandos(Paciente paciente) {
		return memorandoRepository.findByPaciente(paciente);
	}
	
	public Memorando getMemorando(Long id) {
		return memorandoRepository.findById(id).get();
	}
	
}
