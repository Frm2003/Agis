package br.fateczl.com.agis.controller;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fateczl.com.agis.model.Curso;
import br.fateczl.com.agis.model.Turma;
import br.fateczl.com.agis.service.CursoService;
import br.fateczl.com.agis.service.GradeCurricularService;
import br.fateczl.com.agis.service.TurmaService;

@Controller
public class SecretariaGradeCurricularController {
	@Autowired
	private CursoService cserv;
	
	@Autowired
	private GradeCurricularService gcserv;
	
	@Autowired
	private TurmaService tserv;
	
	@GetMapping("secretaria/montarGrade")
	public String get(ModelMap model) {
		model.addAttribute("cursos", cserv.listarTudo());
		return "secretaria/montarGrade";
	}
	
	@PostMapping("secretaria/montarGrade")
	public String post(@RequestParam Map<String, String> param, ModelMap model) {
		Long codCurso = Long.parseLong(param.get("codCurso"));
		Calendar calendar = Calendar.getInstance();
		
		try {
			Curso c = cserv.buscar(codCurso);
			
			model.addAttribute("codCurso", c.getCod());
			model.addAttribute("nomeCurso", c.getNome());
			
			model.addAttribute("ano", calendar.get(Calendar.YEAR));
			model.addAttribute("semestre", gcserv.getSemestre());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("cursos", cserv.listarTudo());
		model.addAttribute("turmas", null);
		
		return "secretaria/montarGrade";
	}
	
	@PostMapping("/secretaria/montarGrade/addTurma")
	public String getAddTurma(@RequestParam Map<String, String> param, RedirectAttributes model) {
		Calendar calendar = Calendar.getInstance();
		Long cod = Long.parseLong(param.get("codCurso"));
		try {
			Curso c = cserv.buscar(cod);
			
			model.addFlashAttribute("codCurso", c.getCod());
			model.addFlashAttribute("nomeCurso", c.getNome());
			
			model.addFlashAttribute("ano", calendar.get(Calendar.YEAR));
			model.addFlashAttribute("semestre", gcserv.getSemestre());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		inserirTurma();
		
		model.addFlashAttribute("cursos", cserv.listarTudo());
		model.addFlashAttribute("turmas", tserv.listarTudo());
		
		return "redirect:/secretaria/montarGrade";
	}
	
	private void inserirTurma() {
		Turma t = new Turma();
		tserv.inserir(t);
	}
}
