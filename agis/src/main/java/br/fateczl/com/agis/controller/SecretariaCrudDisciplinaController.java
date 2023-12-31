package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.fateczl.com.agis.model.Disciplina;
import br.fateczl.com.agis.service.CursoService;
import br.fateczl.com.agis.service.DisciplinaService;
import jakarta.validation.Valid;

@Controller
public class SecretariaCrudDisciplinaController {
	@Autowired
	private CursoService cserv;
	
	@Autowired
	private DisciplinaService dserv;
	
	@GetMapping("/secretaria/disciplina")
	public String get(ModelMap model) {
		model.addAttribute("disciplina", new Disciplina());
		model.addAttribute("cursos", cserv.listarTudo());
		return "secretaria/crudDisciplina/disciplinaForm";
	}
	
	@GetMapping("/secretaria/disciplina/lista")
	public String listar(ModelMap model) throws Exception {
		model.addAttribute("disciplinas", dserv.listarTudo());
		return "secretaria/crudDisciplina/disciplinaTable";
	}
	
	@GetMapping("/secretaria/disciplina/buscar/{cod}")
	public String buscar(@PathVariable("cod") Long cod, ModelMap model) throws Exception {
		model.addAttribute("disciplina", dserv.buscar(cod));
		model.addAttribute("cursos", cserv.listarTudo());
		return "secretaria/crudDisciplina/disciplinaEdit";
	}
	
	@GetMapping("/secretaria/disciplina/deletar/{cod}")
	public String deletar(@PathVariable("cod") Long cod) throws Exception {
		dserv.remover(cod);
		return "redirect:/secretaria/disciplina/lista";
	}
	
	@PostMapping("/secretaria/disciplina/inserir")
	public String Inserir(@Valid Disciplina d) {
		dserv.inserir(d);
		return "redirect:/secretaria/disciplina/lista";
	}
	
	@PostMapping("/secretaria/disciplina/atualizar/{cod}")
	public String atualizar(@Valid Disciplina d) throws Exception {
		dserv.atualizar(d);
		return "redirect:/secretaria/disciplina/lista";
	}
}
