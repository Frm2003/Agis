package br.fateczl.com.agis.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.fateczl.com.agis.model.Aluno;
import br.fateczl.com.agis.service.AlunoService;
import br.fateczl.com.agis.service.CursoService;
import br.fateczl.com.agis.service.GenericServices;
import jakarta.validation.Valid;

@Controller
public class SecretariaAlunoController {
	@Autowired
	private AlunoService aserv;
	
	@Autowired
	private CursoService cserv;
	
	@Autowired
	private GenericServices gserv;
	
	@GetMapping("/secretaria/aluno")
	public String get(ModelMap model) throws Exception {
		model.addAttribute("aluno", new Aluno());
		model.addAttribute("cursos", cserv.listarTudo());
		return "secretaria/crudAluno/alunoForm";
	}
	
	@GetMapping("/secretaria/aluno/lista")
	public String listar(ModelMap model) throws Exception {
		model.addAttribute("alunos", aserv.listarTudo());
		return "secretaria/crudAluno/alunoTable";
	}
	
	@PostMapping("/secretaria/aluno/inserir")
	public String inserir(@Valid Aluno a) throws Exception {
		a.setRa(aserv.geraRa());
		a.setDataMatricula(LocalDate.now());
//		a.setDataLimiteMatricula(aserv.calculaDataLimite());
		a.setEmailCorporativo(gserv.geraEmail(a.getNome()));
		a.setSituacao("ativo");
		
		if (aserv.validarCPF(a.getCpf()) && aserv.validarIdade(a.getDataNasc())) {
			aserv.inserir(a);
		} else {
			throw new Exception("Aluno inválido");
		}
		return "redirect:/secretaria/aluno";
	}
	
	
}
