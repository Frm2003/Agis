package br.fateczl.com.agis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.Disciplina;
import br.fateczl.com.agis.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	@Autowired
	private DisciplinaRepository drep;
	
	public void inserir(Disciplina d) {
		drep.save(d);
	}
	
	public void atualizar(Disciplina d) throws Exception {
		Optional<Disciplina> optional = drep.findById(d.getCod());
		
		if (optional.isPresent()) {
			Disciplina dNew = optional.get();
			
			dNew.setCurso(d.getCurso());
			dNew.setNome(d.getNome());
			dNew.setQtdAulas(d.getQtdAulas());
			dNew.setSemestre(d.getSemestre());
			
			drep.save(dNew);
		} else {
			throw new Exception("Não encontrado");
		}
	}
	
	public Disciplina buscar(Long cod) throws Exception {
		Optional<Disciplina> optional = drep.findById(cod);
		return optional.get();
	}
	
	public void remover(Long cod) throws Exception {
		Disciplina d = buscar(cod);
		d.setCurso(null);
		atualizar(d);
		drep.delete(d);
	}
	
	public List<Disciplina> listarTudo() {
		return drep.findAll();
	}
	
}
