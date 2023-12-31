package br.fateczl.com.agis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.Curso;
import br.fateczl.com.agis.repository.CursoRepository;

@Service
public class CursoService {
	@Autowired
	private CursoRepository crep;
	
	public void inserir(Curso c) {
		crep.save(c);
	}
	
	public void atualizar(Curso c) throws Exception {
		Optional<Curso> optional = crep.findById(c.getCod());
		
		if (optional.isPresent()) {
			Curso cNew = optional.get();
			
			cNew.setNome(c.getNome());
			cNew.setSigla(c.getSigla());
			cNew.setTurno(c.getTurno());
			cNew.setEnade(c.getEnade());
			cNew.setCargaHoraria(c.getCargaHoraria());
			
			crep.save(cNew);
		} else {
			throw new Exception("Não encontrado");
		}
	}
	
	public Curso buscar(Long cod) throws Exception {
		Optional<Curso> optional = crep.findById(cod);
		return optional.get();
	}
	
	public void remover(Long c) {
		crep.deleteById(c);
	}
	
	public List<Curso> listarTudo() {
		return crep.findAll();
	}
}
