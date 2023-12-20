package br.fateczl.com.agis.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	@Column(nullable = false, length = 50)
	private String titulacao;

	@Builder
	public Professor(String cpf, String nome, LocalDate dataNasc, String emailPessoal, String emailCorp,
			String situacao, Long cod, String titulacao) {
		super(cpf, nome, dataNasc, emailPessoal, emailCorp, situacao);
		this.cod = cod;
		this.titulacao = titulacao;
	}
}
