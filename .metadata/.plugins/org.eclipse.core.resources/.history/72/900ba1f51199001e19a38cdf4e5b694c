package br.fateczl.com.agis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.GradeCurricular;
import br.fateczl.com.agis.repository.GradeCurricularRepository;

@Service
public class GradeCurricularService {
	@Autowired
	private GradeCurricularRepository gcrep;
	
	public List<GradeCurricular> listarTudo() {
		return gcrep.findAll();
	}
	
	public int getSemestre() {
		return gcrep.getSemestre();
	}
}
