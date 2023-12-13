package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.fateczl.com.agis.model.GradeCurricular;
import br.fateczl.com.agis.service.DisciplinaService;
import br.fateczl.com.agis.service.GradeCurricularService;
import br.fateczl.com.agis.service.ProfessorService;
import br.fateczl.com.agis.service.TurmaService;

@Controller
public class SecretariaGradeCurricularEditarController {
	@Autowired
	private DisciplinaService dserv;
	
	@Autowired
	private GradeCurricularService gcserv;
	
	@Autowired
	private ProfessorService pserv;
	
	@Autowired
	private TurmaService tserv;
	
	@GetMapping("secretaria/grade/editar/{cod}")
	public String getGrde(@PathVariable("cod") Long cod, ModelMap model) {
		GradeCurricular gc = gcserv.buscar(cod);
		
		model.addAttribute("codCurso", gc.getCurso());
		model.addAttribute("nomeCurso", gc.getCurso().getNome());
		model.addAttribute("ano", gc.getAno());
		model.addAttribute("semestre", gc.getSemestre());
		model.addAttribute("turmas", tserv.listarTurmasDaGrade(gc.getCod()));
		
		model.addAttribute("disciplinas", dserv.listarTudo());
		model.addAttribute("professores", pserv.listarTudo());
		
		return "secretaria/crudGrade/editarGrade";
	}
}
