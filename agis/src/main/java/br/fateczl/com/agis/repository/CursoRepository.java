package br.fateczl.com.agis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fateczl.com.agis.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
}
