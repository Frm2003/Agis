package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.fateczl.com.agis.model.Professor;
import br.fateczl.com.agis.service.GenericServices;
import br.fateczl.com.agis.service.ProfessorService;
import jakarta.validation.Valid;

@Controller
public class SecretariaProfessorController {
	@Autowired
	private ProfessorService pserv;
	
	@Autowired
	private GenericServices gserv;
	
	@GetMapping("/secretaria/professor")
	public String get(ModelMap model) {
		model.addAttribute("professor", new Professor());
		return "secretaria/crudProfessor/professorForm";
	}
	
	@GetMapping("/secretaria/professor/lista")
	public String listar(ModelMap model) throws Exception {
		model.addAttribute("professores", pserv.listarTudo());
		return "secretaria/crudProfessor/professorTable";
	}
	
	@GetMapping("/secretaria/professor/buscar/{cod}")
	public String buscar(@PathVariable("cod") Long cod, ModelMap model) throws Exception {
		model.addAttribute("professor", pserv.buscar(cod));
		return "secretaria/crudProfessor/professorEdit";
	}
	
	@GetMapping("/secretaria/professor/deletar/{cod}")
	public String deletar(@PathVariable("cod") Long cod) throws Exception {
		pserv.remover(cod);
		return "redirect:/secretaria/professor/lista";
	}
	
	@PostMapping("/secretaria/professor/inserir")
	public String Inserir(@Valid Professor p) {
		p.setEmailCorp(gserv.geraEmail(p.getNome()));
		pserv.inserir(p);
		return "redirect:/secretaria/professor/lista";
	}
	
	@PostMapping("/secretaria/professor/atualizar/{cod}")
	public String atualizar(@Valid Professor p) throws Exception {
		pserv.atualizar(p);
		return "redirect:/secretaria/professor/lista";
	}
}
