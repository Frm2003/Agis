package br.fateczl.com.agis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.Turma;
import br.fateczl.com.agis.repository.TurmaRepository;

@Service
public class TurmaService {
	@Autowired
	private TurmaRepository trep;
	
	public void inserir(Turma t) {
		trep.save(t);
	}
	
	public void atualizar(Turma t) throws Exception {
		Optional<Turma> optional = trep.findById(t.getCod());
		
		if (optional.isPresent()) {
			Turma tNew = optional.get();
			
			tNew.setDisciplina(t.getDisciplina());
			tNew.setProfessor(t.getProfessor());
			tNew.setHorarioInicio(t.getHorarioInicio());
			tNew.setHorarioFim(t.getHorarioFim());
			tNew.setDiaDaSemana(t.getDiaDaSemana());
			
			trep.save(tNew);
		} else {
			throw new Exception("Não encontrado");
		}
	}
	
	public Turma buscar(Long cod) throws Exception {
		Optional<Turma> optional = trep.findById(cod);
		return optional.get();
	}
	
	public void remover(Turma t) throws Exception {
		t.setDisciplina(null);
		t.setProfessor(null);
		t.setGrade(null);
		atualizar(t);
		trep.delete(t);
	}
	
	public Long contador() {
		return trep.count();
	}
	
	public List<Turma> listarTudo() {
		return trep.findAll();
	}
	
	public List<Turma> listarTurmasDoAluno(String ra) {
		return trep.listarTurmasDoAluno(ra);
	}
	
	public List<Turma> listarTurmaNaoMatriculadas(String ra) {
		return trep.listarTurmaNaoMatriculadas(ra);
	}
	
	public List<Turma> listarTurmasDaGrade(Long cod) {
		return trep.listarTurmasDaGrade(cod);
	}
	
	public List<Turma> listarTurmasComNull() {
		return trep.listarTurmasComNull();
	}
	
}
