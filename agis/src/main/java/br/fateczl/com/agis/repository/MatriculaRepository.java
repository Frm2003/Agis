package br.fateczl.com.agis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.fateczl.com.agis.model.Matricula;
import br.fateczl.com.agis.model.pk.MatriculaPk;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, MatriculaPk> {
   
	@Query(value = "select m.* from Matricula m where m.raAluno = ?", nativeQuery = true)
	List<Matricula> listarMatriculasPorRa(@Param("ra") String ra);
}
