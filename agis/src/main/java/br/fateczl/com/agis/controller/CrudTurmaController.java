package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.fateczl.com.agis.model.Turma;
import br.fateczl.com.agis.service.DisciplinaService;
import br.fateczl.com.agis.service.ProfessorService;
import br.fateczl.com.agis.service.TurmaService;
import jakarta.validation.Valid;

@Controller
public class CrudTurmaController {
	@Autowired
	private DisciplinaService dserv;
	
	@Autowired
	private ProfessorService pserv;
	
	@Autowired
	private TurmaService tserv;
	
	
	@GetMapping("/secretaria/turma")
	public String get(ModelMap model) {
		model.addAttribute("turma", new Turma());
		model.addAttribute("disciplinas", dserv.listarTudo());
		model.addAttribute("professores", pserv.listarTudo());
		return "secretaria/crudTurma/turmaForm";
	}
	
	@GetMapping("/secretaria/turma/lista")
	public String listar(ModelMap model) throws Exception {
		model.addAttribute("turmas", tserv.listarTudo());
		return "secretaria/crudTurma/turmaTable";
	}
	
	@GetMapping("/secretaria/turma/buscar/{cod}")
	public String buscar(@PathVariable("cod") Long cod, ModelMap model) throws Exception {
		model.addAttribute("turma", tserv.buscar(cod));
		model.addAttribute("disciplinas", dserv.listarTudo());
		model.addAttribute("professores", pserv.listarTudo());
		return "secretaria/crudTurma/turmaEdit";
	}
	
	@GetMapping("/secretaria/turma/deletar/{cod}")
	public String deletar(@PathVariable("cod") Long cod) throws Exception {
		tserv.remover(cod);
		return "redirect:/secretaria/turma/lista";
	}
	
	@PostMapping("/secretaria/turma/inserir")
	public String Inserir(@Valid Turma t) {
		tserv.inserir(t);
		return "redirect:/secretaria/turma/lista";
	}
	
	@PostMapping("/secretaria/turma/atualizar/{cod}")
	public String atualizar(@Valid Turma t) throws Exception {
		tserv.atualizar(t);
		return "redirect:/secretaria/turma/lista";
	}
}
