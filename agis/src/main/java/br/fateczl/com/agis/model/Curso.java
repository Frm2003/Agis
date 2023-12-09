package br.fateczl.com.agis.model;

import java.math.BigDecimal;

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
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long cod;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false)
	private int cargaHoraria;
	
	@Column(nullable = false, length = 100)
	private String sigla;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal enade;
	
	@Column(nullable = false, length = 20)
	private String turno;
}
