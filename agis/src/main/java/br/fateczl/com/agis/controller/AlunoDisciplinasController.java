package br.fateczl.com.agis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoDisciplinasController {
	@GetMapping("/aluno/disciplinas")
	public String get() {
		return "alunos/alunoDisciplinas";	
	}
}
