package com.example.demo.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Conta;
import com.example.demo.service.ContaService;

@RestController
@RequestMapping("/v1/contas")
public class ContaController {

	@Autowired
	ContaService contaService;
	
	@GetMapping
	public ResponseEntity<ArrayList<Conta>> findAll(){
		
		ArrayList<Conta> listaRetorno = contaService.findAll();
		return new ResponseEntity<ArrayList<Conta>>(listaRetorno,HttpStatus.OK);
	}
}
