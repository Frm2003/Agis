package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.fateczl.com.agis.model.GradeCurricular;
import br.fateczl.com.agis.model.Turma;
import br.fateczl.com.agis.service.DisciplinaService;
import br.fateczl.com.agis.service.GradeCurricularService;
import br.fateczl.com.agis.service.ProfessorService;
import br.fateczl.com.agis.service.TurmaService;
import jakarta.validation.Valid;

@Controller
public class SecretariaGradeCurricularTurmaController {
	@Autowired
	private DisciplinaService dserv;
	
	@Autowired
	private GradeCurricularService gcserv;
	
	@Autowired
	private ProfessorService pserv;
	
	@Autowired
	private TurmaService tserv;
	
	// SERVE PARA ADICIONAR UMA TURMA NOVA
	
	@GetMapping("/secretaria/grade/adicionar/turma/{cod}")
	public String adicionar(@PathVariable("cod") Long codGrade, ModelMap model) {
		GradeCurricular gc = gcserv.buscar(codGrade);
		
		Turma t = new Turma();
		t.setGrade(gc);
		
		model.addAttribute("turma", t);
		model.addAttribute("disciplinas", dserv.listarTudo());
		model.addAttribute("professores", pserv.listarTudo());
		
		return "secretaria/crudGrade/turmaForm";
	}
	
	@PostMapping("/secretaria/grade/adicionar/turma")
	public String adicionar(@Valid Turma t) {
		Long codGrade = t.getGrade().getCod();
		tserv.inserir(t);
		return "redirect:/secretaria/grade/visualizar/" + codGrade;
	}
	
	// SERVE APENAS PARA DELETAR E DA UM RELOAD NA PRÓPRIA PÁGINA
	
	@GetMapping("/secretaria/grade/deletar/turma/{cod}")
	public String deletar(@PathVariable("cod") Long cod) throws Exception {
		Turma t = tserv.buscar(cod);
		Long codGrade = t.getGrade().getCod();
		tserv.remover(t);
		return "redirect:/secretaria/grade/visualizar/" + codGrade;
	}
	
	// RELACIONADO A TELA DE EDIÇÃO DE TURMA - turmaEdit.html
	
	@GetMapping("/secretaria/grade/editar/turma/{cod}")
	public String buscar(@PathVariable("cod") Long cod, ModelMap model) throws Exception {
		Turma t = tserv.buscar(cod);
		model.addAttribute("turma", t);
		model.addAttribute("disciplinas", dserv.listarTudo());
		model.addAttribute("professores", pserv.listarTudo());
		return "secretaria/crudGrade/turmaEdit";
	}
	
	@PostMapping("/secretaria/grade/editar/turma/")
	public String atualizar(@Valid Turma t, ModelMap model) throws Exception {
		tserv.atualizar(t);
		
		GradeCurricular gc = gcserv.buscar(t.getGrade().getCod());
	
		model.addAttribute("grade", gc);
		model.addAttribute("turmas", tserv.listarTurmasDaGrade(gc.getCod()));
		
		return "redirect:/secretaria/grade/visualizar/" + gc.getCod();
	}
	
}
