package br.fateczl.com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fateczl.com.agis.model.Datas;
import br.fateczl.com.agis.service.DatasService;
import jakarta.validation.Valid;

@Controller
public class SecretariaCrudDataController {
	@Autowired
	private DatasService dserv;
	
	@GetMapping("/secretaria/data")
	public String get(ModelMap model) {
		model.addAttribute("date", new Datas());
		model.addAttribute("datas", dserv.buscaTudo());
		return "secretaria/crudDatas/dataCrud";
	}
	
	@PostMapping("/secretaria/data/inserir") 
	public String inserir(@Valid Datas d, RedirectAttributes model) {
		dserv.inserir(d);
		model.addFlashAttribute("date", new Datas());
		model.addFlashAttribute("datas", dserv.buscaTudo());
		return "redirect:/secretaria/data";
	}
	
}
