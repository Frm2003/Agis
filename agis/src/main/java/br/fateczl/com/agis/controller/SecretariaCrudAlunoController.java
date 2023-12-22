package br.fateczl.com.agis.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.fateczl.com.agis.model.Aluno;
import br.fateczl.com.agis.model.Curso;
import br.fateczl.com.agis.service.AlunoService;
import br.fateczl.com.agis.service.CursoService;
import br.fateczl.com.agis.service.GenericService;

@Controller
public class SecretariaCrudAlunoController {
	@Autowired
	private AlunoService aserv;

	@Autowired
	private CursoService cserv;

	@Autowired
	private GenericService gserv;

	@GetMapping("/secretaria/aluno/matricula")
	public String get(ModelMap model) {
		model.addAttribute("cursos", cserv.listarTudo());
		return "secretaria/crudAluno/alunoForm";
	}

	@PostMapping("/secretaria/aluno/inserir")
	public String post(@RequestParam Map<String, String> param, ModelMap model) {
		String nome = param.get("nome");
		String dataNasc = param.get("dataNasc");
		String nomeSocial = param.get("nomeSocial");
		String emailPessoal = param.get("emailPessoal");
		String dataConc2grau = param.get("dataConc2grau");
		String cpf = param.get("cpf");
		String instConc2grau = param.get("instConc2grau");
		String ptVestibular = param.get("ptVestibular");
		String posVestibular = param.get("posVestibular");
		String codCurso = param.get("codCurso");

		inserir(nome, LocalDate.parse(dataNasc), nomeSocial, emailPessoal, LocalDate.parse(dataConc2grau), cpf,
				instConc2grau, Integer.parseInt(ptVestibular), Integer.parseInt(posVestibular),
				Long.parseLong(codCurso));
		
		model.addAttribute("cursos", cserv.listarTudo());
		
		return "redirect:/secretaria/aluno/matricula";
	}

	private void inserir(String nome, LocalDate dataNasc, String nomeSocial, String emailPessoal,
			LocalDate dataConc2grau, String cpf, String instConc2grau, int ptVestibular, int posVestibular,
			Long codCurso) {
		
		String emailCorp = gserv.geraEmail(nome);
		String ra = aserv.geraRa();
		LocalDate hoje = LocalDate.now();
		LocalDate dataLimite = aserv.calculaDataLimite();
		
		
	}
}
