package br.fateczl.com.agis.model.pk;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

public class GradeCurricularPk implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ano;
	private int semestre;
}
