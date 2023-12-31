package br.fateczl.com.agis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.fateczl.com.agis.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long>{
	
	@Query(value = "select t.* from turma t inner join matricula m on t.cod = m.codTurma where m.raAluno = ?", nativeQuery = true)
	List<Turma> listarTurmasDoAluno(@Param("ra") String ra);
	
	@Query(value = "select t.* from turma t left join matricula m on t.cod = m.codTurma and m.raAluno = ? where m.raAluno is null", nativeQuery = true)
	List<Turma> listarTurmaNaoMatriculadas(@Param("ra") String ra);

	@Query(value = "select t.* from turma t inner join GradeCurricular gc on t.codGrade = gc.cod where gc.cod = ?", nativeQuery = true)
	List<Turma> listarTurmasDaGrade(Long cod);
	
	@Query(value = "select * from turma where codDisciplina is null", nativeQuery = true)
	List<Turma> listarTurmasComNull();
}

