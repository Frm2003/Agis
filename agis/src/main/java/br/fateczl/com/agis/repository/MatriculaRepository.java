package br.fateczl.com.agis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fateczl.com.agis.model.Matricula;
import br.fateczl.com.agis.model.pk.MatriculaPk;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
   Optional<Matricula> findById(MatriculaPk id);
}
