package br.fateczl.com.agis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fateczl.com.agis.model.Planejamento;
import br.fateczl.com.agis.model.pk.PlanejamentoPk;

@Repository
public interface PlanejamentoRepository extends JpaRepository<Planejamento, Long> {
   Optional<Planejamento> findById(PlanejamentoPk id);
}
