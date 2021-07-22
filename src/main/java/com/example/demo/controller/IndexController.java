package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Define um bean Controller
@RestController
@RequestMapping("/index")
public class IndexController {
	
	@GetMapping
	public String bemVindo() {
		return "Bem vindo !";
	}

	@GetMapping("/nome")
	public String bemVindoNome(@RequestParam("name") String nome,
			@RequestParam("cidade") String cidade,
			@RequestParam("estado") String estado) {
		
		return "Bem Vindo " + nome + ". Como está o tempo aí em " + cidade + estado + "?";
	}
	
		
	
}
