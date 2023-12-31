package br.fateczl.com.agis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.fateczl.com.agis.service.TurmaService;

@Controller
public class AlunoRematriculaController {
	@Autowired
	private TurmaService tserv;
	
	@GetMapping("/aluno/rematriculas")
	public String get(@RequestParam Map<String, String> param, ModelMap model) {
		model.addAttribute("ra", param.get("ra"));
		model.addAttribute("turmas", tserv.listarTurmaNaoMatriculadas(param.get("ra")));
		return "alunos/alunoRematricula";
	}
	
}
