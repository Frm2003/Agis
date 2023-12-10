package br.fateczl.com.agis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorNotasController {
   @GetMapping("/professor/notas")
   public String getNotas() {
      return "professores/professorNotas";
   }

   @GetMapping("/professor/planoAula")
   public String getPlano() {
      return "professores/planoAula";
   }

}