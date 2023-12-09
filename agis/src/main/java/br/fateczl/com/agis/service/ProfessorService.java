package br.fateczl.com.agis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.Professor;
import br.fateczl.com.agis.repository.ProfessorRepository;

@Service
public class ProfessorService {
	@Autowired
	private ProfessorRepository prep;
	
	public void inserir(Professor p) {
		prep.save(p);
	}
	
	public void atualizar(Professor p) throws Exception {
		Optional<Professor> optional = prep.findById(p.getCod());
		
		if (optional.isPresent()) {
			Professor pNew = optional.get();
			
			pNew.setNome(p.getNome());
			pNew.setTitulacao(p.getTitulacao());
			pNew.setEmailPessoal(p.getEmailPessoal());
			
			prep.save(pNew);
		} else {
			throw new Exception("Não encontrado");
		}
	}
	
	public Professor buscar(Long cod) throws Exception {
		Optional<Professor> optional = prep.findById(cod);
		return optional.get();
	}
	
	public void remover(Long cod) {
		prep.deleteById(cod);
	}
	
	public List<Professor> listarTudo() {
		return prep.findAll();
	}
}
