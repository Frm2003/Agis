package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.fateczl.com.agis.service.GradeCurricularService;

@Controller
public class SecretariaGradeCurricularVizualizaController {
	@Autowired
	private GradeCurricularService gcserv;
	
	@GetMapping("secretaria/grade")
	public String get(ModelMap model) {
		model.addAttribute("grades", gcserv.listarTudo());
		return "secretaria/crudGrade/visualizaGrade";
	}
}
