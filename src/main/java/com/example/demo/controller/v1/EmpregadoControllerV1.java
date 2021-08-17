package com.example.demo.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmpregadoDTO;
import com.example.demo.models.Empregado;
import com.example.demo.service.EmpregadoService;

@RestController
@RequestMapping("/v1/empregados")
@CrossOrigin
public class EmpregadoControllerV1 {

	@Autowired
	EmpregadoService empregadoService;
	
	@GetMapping
	public ResponseEntity<Page<EmpregadoDTO>> buscarTodos(
			@RequestParam(required = false) Integer cpf,
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String nomeAgencia,
			@RequestParam(required = false, defaultValue = "5") Integer qtdItensPagina,
			@RequestParam(required = false, defaultValue = "0") Integer numeroPagina,
			@RequestParam(required = false, defaultValue = "ASC") String direcaoOrdenacao,
			@RequestParam(required = false, defaultValue = "nome") String campoOrdem){
		
		//return empregadoService.findAll(cpf,nome,nomeAgencia,qtdItensPagina,numeroPagina,direcaoOrdenacao,campoOrdem);
		return new ResponseEntity<Page<EmpregadoDTO>>(
				empregadoService.findAll(cpf,nome,nomeAgencia,qtdItensPagina,numeroPagina,direcaoOrdenacao,campoOrdem),HttpStatus.OK);
	}
	
	
	@GetMapping("/{cpf}")
	public ResponseEntity<EmpregadoDTO> buscarPorCpf(@PathVariable Integer cpf){
	
		return new ResponseEntity<EmpregadoDTO>(
				empregadoService.findById(cpf), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> salvar(@RequestBody Empregado e){
		
		empregadoService.save(e);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	@CacheEvict(value = "empregado", allEntries = true)
	@GetMapping("/evict")
	public void evictAllCacheValues() {}
}
