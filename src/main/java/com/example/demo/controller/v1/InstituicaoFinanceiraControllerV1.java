package com.example.demo.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.InstituicaoFinanceira;
import com.example.demo.service.InstituicaoService;

//Define um bean rest controller que vai ser instanciado pelo Spring
@RestController
//Criamos a rota para a nossa classe/cardapio
@RequestMapping("/v1/instituicoes")
public class InstituicaoFinanceiraControllerV1 {

	//Ponto de injeção do service
	@Autowired
	InstituicaoService instituicaoService;
	
	//Rota padrão que irá buscar todas
	@GetMapping
	public ArrayList<InstituicaoFinanceira> buscarTodos(){
		
		return instituicaoService.findAll();
	}
	
	@GetMapping("/{identifier}")
	public InstituicaoFinanceira buscarPorId(@PathVariable Integer identifier) {
		return instituicaoService.findById(identifier);
	}
	
	@DeleteMapping("/{identifier}")
	public void deletarPorId(@PathVariable Integer identifier) {
		instituicaoService.deleteById(identifier);
	}

	@PostMapping
	public void inserir(@RequestBody InstituicaoFinanceira instituicao) {
		instituicaoService.save(instituicao);
	}
	
	@PutMapping
	public void atualizar(@RequestBody InstituicaoFinanceira instituicao) {
		instituicaoService.update(instituicao);
	}
	
}
