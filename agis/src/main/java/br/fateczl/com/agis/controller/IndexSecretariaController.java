package br.fateczl.com.agis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexSecretariaController {
	
	@GetMapping("/secretaria")
	public String get() {
		return "secretaria/indexSecretaria";
	}
}
