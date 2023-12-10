package br.fateczl.com.agis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.Planejamento;
import br.fateczl.com.agis.model.pk.PlanejamentoPk;
import br.fateczl.com.agis.repository.PlanejamentoRepository;

@Service
public class PlanejamentoService {
   @Autowired
   private PlanejamentoRepository prep;

   public void inserir(Planejamento p) {
      prep.save(p);
   }

   public void atualizar(Planejamento p) throws Exception {
      Optional<Planejamento> optional = prep.findById(
            new PlanejamentoPk(p.getProfessor(), p.getDisciplina(), p.getDataAula()));

      if (optional.isPresent()) {
         Planejamento pNew = optional.get();

         pNew.setDataAula(p.getDataAula());
         pNew.setConteudo(p.getConteudo());
         pNew.setProfessor(p.getProfessor());
         pNew.setDisciplina(p.getDisciplina());

         prep.save(pNew);
      } else {
         throw new Exception("Não encontrado");
      }
   }

   public List<Planejamento> listarTudo() {
      return prep.findAll();
   }

}