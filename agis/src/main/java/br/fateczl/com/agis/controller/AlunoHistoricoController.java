package br.fateczl.com.agis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.fateczl.com.agis.service.MatriculaService;

@Controller
public class AlunoHistoricoController {
	@Autowired
	private MatriculaService mserv;
	
	@GetMapping("/aluno/historico")
	public String get(@RequestParam Map<String, String> param, ModelMap model) {
		model.addAttribute("ra", param.get("ra"));
		model.addAttribute("matriculas", mserv.listarMatriculasPorRa(param.get("ra")));
		return "alunos/alunoHistorico";
	}
	
}
