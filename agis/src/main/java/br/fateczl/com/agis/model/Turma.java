package br.fateczl.com.agis.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long cod;
	
	@Column(nullable = true)
	private String horarioInicio;
	
	@Column(nullable = true)
	private String horarioFim;
	
	@Column(nullable = true, length = 15)
	private String diaDaSemana;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Disciplina.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "codDisciplina", nullable = true)
	private Disciplina disciplina;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Professor.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "codProfessor", nullable = true)
	private Professor professor;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = GradeCurricular.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "codGrade", nullable = true)
	private GradeCurricular grade;
}
