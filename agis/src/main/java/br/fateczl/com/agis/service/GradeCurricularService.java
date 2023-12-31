package br.fateczl.com.agis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.GradeCurricular;
import br.fateczl.com.agis.repository.GradeCurricularRepository;

@Service
public class GradeCurricularService {
	@Autowired
	private GradeCurricularRepository gcrep;
	
	public void inserir(GradeCurricular g) {
		gcrep.save(g);
	}
	
	public GradeCurricular buscar(Long cod) {
		Optional<GradeCurricular> optional = gcrep.findById(cod);
		return optional.get();
	}
	
	public List<GradeCurricular> listarTudo() {
		return gcrep.findAll();
	}
	
	public int getSemestre() {
		return gcrep.getSemestre();
	}
}
