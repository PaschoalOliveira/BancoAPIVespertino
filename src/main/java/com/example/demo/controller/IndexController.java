package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Cliente;
import com.example.demo.service.CalculadoraService;
import com.example.demo.service.ClienteService;

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
