package br.fateczl.com.agis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import br.fateczl.com.agis.model.GradeCurricular;

@Repository
public interface GradeCurricularRepository extends JpaRepository<GradeCurricular, Long>{
	
	@Procedure(name = "GradeCurricular.getSemetre")
	int getSemestre();

}
