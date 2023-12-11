package br.fateczl.com.agis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.fateczl.com.agis.service.CursoService;
import br.fateczl.com.agis.service.TurmaService;

@Controller
public class SecretariaGradeCurricularController {
	@Autowired
	private CursoService cserv;
	
	@Autowired
	private TurmaService tserv;
	
	@GetMapping("secretaria/montarGrade")
	public String get(ModelMap model) {
		model.addAttribute("cursos", cserv.listarTudo());
		return "secretaria/montarGrade";
	}
	
	@PostMapping("secretaria/montarGrade/{codCurso}")
	public String post(@RequestParam Map<String, String> param, ModelMap model) {
		Long codCurso = Long.parseLong(param.get("codCurso"));
		model.addAttribute("turmas", tserv.listarTurmasDoCurso(codCurso));
		return "secretaria/montarGrade";
	}
}
