package com.example.demo.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.InstituicaoFinanceira;
import com.example.demo.service.InstituicaoService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	public ResponseEntity<ArrayList<InstituicaoFinanceira>> buscarTodos(){
		
		//Retorna um ResponseEntity com o retorno do service o Status HTTP
		return new ResponseEntity<ArrayList<InstituicaoFinanceira>>(instituicaoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{identifier}")
	public ResponseEntity<InstituicaoFinanceira> buscarPorId(@PathVariable Integer identifier) {
		InstituicaoFinanceira instituicaoRetorno = instituicaoService.findById(identifier);
		return new ResponseEntity<InstituicaoFinanceira>(instituicaoRetorno,HttpStatus.OK);
	}
	
	//Rota para pesquisar por nome. Recebe como parâmetro o nome a ser pesquisado
	@GetMapping("/nome")
	public ResponseEntity<ArrayList<InstituicaoFinanceira>> buscarPorNome(@RequestParam String nome){
		
		ArrayList<InstituicaoFinanceira> instituicoes = instituicaoService.findByName(nome);
		//Retorna uma lista com as instituicoes com aquele nome e o status ok
		return new ResponseEntity<ArrayList<InstituicaoFinanceira>>(instituicoes, HttpStatus.OK);
	}
	
	@DeleteMapping("/{identifier}")
	public void deletarPorId(@PathVariable Integer identifier) {
		instituicaoService.deleteById(identifier);
	}

	@PostMapping
	public ResponseEntity inserir(@RequestBody InstituicaoFinanceira instituicao) {
		instituicaoService.save(instituicao);
		return new ResponseEntity(HttpStatus.CREATED);
		//return ResponseEntity.status(HttpStatus.CREATED).body("Instituição salva com sucesso");
	}
	
	//Anotação responsável por definir os retornos dos erros 
	//que serão visualizados no Swagger
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Atualizou a instituição"),
		    @ApiResponse(code = 422, message = "Instituição informada não existe"),
		})
	@PutMapping
	public ResponseEntity atualizar(@RequestBody InstituicaoFinanceira instituicao) {
		
		try {
			instituicaoService.update(instituicao);
			//Retorna o STATUS CODE OK com a instituicao no body da requisição
			return ResponseEntity.status(HttpStatus.OK).body(instituicao);
			//return ResponseEntity.ok(instituicao);
		} catch (Exception e) {
			//Retorna um erro indicando que a isntituição não existe
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Instituição informada não existe");
		}
	}
	
}
