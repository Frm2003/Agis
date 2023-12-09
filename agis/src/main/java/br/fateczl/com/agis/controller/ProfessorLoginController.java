package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fateczl.com.agis.model.Professor;
import br.fateczl.com.agis.service.ProfessorService;
import jakarta.validation.Valid;

@Controller
public class ProfessorLoginController {
   @Autowired
   private ProfessorService pserv;

   @GetMapping("/professor/login")
   public String get(ModelMap model) {
      model.addAttribute("professor", new Professor());
      return "professores/professorLogin";
   }

   @PostMapping("/professor/logar")
   public String post(@Valid Professor p, RedirectAttributes model) {
      for (Professor professor : pserv.listarTudo()) {
         if (professor.getNome().equals(p.getNome())) {
            model.addFlashAttribute("nome", professor.getNome());
            return "redirect:/professor/notas";
         }
      }
      return null;
   }
}
