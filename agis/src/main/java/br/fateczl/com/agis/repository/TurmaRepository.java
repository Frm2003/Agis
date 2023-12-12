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

	@Query(value = "select t.* from turma t inner join Disciplina d on t.codDisciplina = d.cod inner join Curso c on c.cod = d.codCurso where c.cod = 1", nativeQuery = true)
	List<Turma> listarTurmasDoCurso(Long cod);
	
	@Query(value = "select * from turma where codDisciplina is null", nativeQuery = true)
	List<Turma> listarTurmasComNull();
}

