package br.fateczl.com.agis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.Matricula;
import br.fateczl.com.agis.model.pk.MatriculaPk;
import br.fateczl.com.agis.repository.MatriculaRepository;

@Service
public class MatriculaService {
   @Autowired
   private MatriculaRepository mrep;

   public void inserir(Matricula m) {
      mrep.save(m);
   }

   public void atualizar(Matricula m) throws Exception {
      Optional<Matricula> optional = mrep.findById(new MatriculaPk(m.getAluno(), m.getTurma(), m.getAno(), m.getSemestre()));

      if (optional.isPresent()) {
         Matricula mNew = optional.get();

         mNew.setAluno(m.getAluno());
         mNew.setTurma(m.getTurma());
         mNew.setAno(m.getAno());
         mNew.setSemestre(m.getSemestre());
         mNew.setSituacao(m.getSituacao());

         mrep.save(mNew);
      } else {
         throw new Exception("Não encontrado");
      }
   }

   public List<Matricula> listarTudo() {
      return mrep.findAll();
   }
   
   public List<Matricula> listarMatriculasPorRa(String ra) {
      return mrep.listarMatriculasPorRa(ra);
   }

}