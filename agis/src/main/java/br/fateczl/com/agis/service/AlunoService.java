package br.fateczl.com.agis.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.Aluno;
import br.fateczl.com.agis.repository.AlunoRepository;

@Service	
public class AlunoService {
	@Autowired
	private AlunoRepository arep;
	
	public void inserir(Aluno a) {
		arep.save(a);
	}
	
	public Aluno buscar(String ra) throws Exception {
		Optional<Aluno> optional = arep.findById(ra);
		return optional.get();
	}
	
	public void remover(Aluno a) {
		arep.delete(a);
	}
	
	public List<Aluno> listarTudo() {
		return arep.findAll();
	}
	
	public String geraRa() {
		return arep.geraRa();
	}
	
	public LocalDate calculaDataLimite() {
		return arep.calculaDataLimite();
	}
	
	public boolean validarIdade(LocalDate dataNasc) {
		return arep.validarIdade(dataNasc);
	}
	
	public boolean validarCPF(String cpf) {
		return arep.validarCPF(cpf);
	}
}
