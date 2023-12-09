package br.fateczl.com.agis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 50)
	private String titulacao;
	
	@Column(nullable = false, length = 30)
	private String emailPessoal;
	
	@Column(nullable = false, length = 30)
	private String emailCorp;	
}
