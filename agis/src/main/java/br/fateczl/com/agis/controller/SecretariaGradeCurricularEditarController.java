package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.fateczl.com.agis.model.GradeCurricular;
import br.fateczl.com.agis.service.GradeCurricularService;
import br.fateczl.com.agis.service.TurmaService;

@Controller
public class SecretariaGradeCurricularEditarController {
	@Autowired
	private GradeCurricularService gcserv;
	
	@Autowired
	private TurmaService tserv;
	
	@GetMapping("secretaria/grade/visualizar/{cod}")
	public String getGrde(@PathVariable("cod") Long cod, ModelMap model) {
		GradeCurricular gc = gcserv.buscar(cod);
		
		model.addAttribute("grade", gc);
		model.addAttribute("turmas", tserv.listarTurmasDaGrade(gc.getCod()));
		
		return "secretaria/crudGrade/editarGrade";
	}
}
