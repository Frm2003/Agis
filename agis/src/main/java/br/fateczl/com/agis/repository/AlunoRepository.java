package br.fateczl.com.agis.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.fateczl.com.agis.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String>  {
	@Procedure(name = "Aluno.geraRa")
	String geraRa();
	
	@Procedure(name = "Aluno.calculaDataLimite")
	LocalDate calculaDataLimite();
	
	@Procedure(name = "Aluno.validarCPF")
	boolean validarCPF(@Param("cpf") String cpf);
	
	@Procedure(name = "Aluno.validarIdade")
	boolean validarIdade(@Param("dataNasc") LocalDate dataNasc);
}
