package br.fateczl.com.agis.model.pk;

import java.io.Serializable;

import br.fateczl.com.agis.model.Aluno;
import br.fateczl.com.agis.model.Turma;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Embeddable
public class MatriculaPk implements Serializable {
	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	private Turma turma;
	private int ano;
	private int semestre;
}
