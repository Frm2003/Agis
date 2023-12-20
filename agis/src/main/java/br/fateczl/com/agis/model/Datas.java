package br.fateczl.com.agis.model;

import java.time.LocalDate;

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
public class Datas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	@Column(nullable = false, columnDefinition = "DATE")
	private LocalDate data;	
	
	@Column(nullable = false, length = 500)
	private String descricao;
	
	@Column(nullable = false)
	private boolean ehFeriado;
	
	@Column(nullable = false)
	private int ano;
}
