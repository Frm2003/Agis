package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fateczl.com.agis.model.Aluno;
import br.fateczl.com.agis.service.AlunoService;
import br.fateczl.com.agis.service.TurmaService;
import jakarta.validation.Valid;

@Controller
public class AlunoLoginController {
	@Autowired
	private AlunoService aserv;
	
	@Autowired
	private TurmaService tserv;	
	
	@GetMapping("/aluno/login")
	public String get(ModelMap model) {
		model.addAttribute("aluno", new Aluno());
		return "alunos/alunoLogin";
	}
	
	@PostMapping("/aluno/logar")
	public String post(@Valid Aluno a, RedirectAttributes model) {
		for (Aluno aluno : aserv.listarTudo()) {
			if (aluno.getRa().equals(a.getRa())) {
				model.addFlashAttribute("ra", aluno.getRa());
				model.addFlashAttribute("turmas", tserv.listarTurmasDoAluno(aluno.getRa()));
				return "redirect:/aluno/disciplinas";	
			}
		}
		return null;
	}
}
