package com.example.demo.controller.v2;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Cliente;
import com.example.demo.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/clientes")
//Controller responsável pelo recurso Cliente
public class ClienteControllerV2 {

	//Ponto de injeção que permite utilziar o bean nesta classe
	@Autowired
	ClienteService clienteService;

	@ApiOperation(value = "Retorna uma lista de pessoas")
	@GetMapping
	public ArrayList<Cliente> pesquisarTodos(
			@RequestParam int page,
			@RequestParam int size) {
		return clienteService.findAll(page, size);
	}
	
	@ApiOperation(value = "Retrona uma slita de clientes por ID")
	//Criar uma rota para resgatar Cliente por ID
	@GetMapping("/{cpf}")
	public Cliente resgataClientePorId(@PathVariable Integer cpf) {
		return clienteService.findById(cpf);
	}
	
	//Criando nova rota
	@GetMapping("/pesquisarNome")
	public String pesquisarNome(@RequestParam("mat") Integer matricula) {

		//Realiza a chamada à camada service 
		String nome = clienteService.pesquisarNomeService(matricula);
		return nome;
	}

	//Exercício dde 27/07 para consulta de clientes por qualquer parte do telefone
	@GetMapping("/telefone/{numero}")
	public ResponseEntity<ArrayList<Cliente>> 
					pesquisarPorNumero(@PathVariable String numero){
		
		ArrayList<Cliente> listRetorno = clienteService.pesquisarPorNumero(numero);
		
		return new ResponseEntity<ArrayList<Cliente>>(listRetorno,HttpStatus.OK);
	}
	
	//Criando nova rota para pesquisa de Clientes
	@GetMapping("/pesquisarCliente")
	public Cliente pesquisarCliente(@RequestParam("cpf") Integer cpf) {

		//Realiza a chamada à camada service 
		Cliente cliente = clienteService.pesquisarClienteService(cpf);
		return cliente;
	}

	//Cria a rota para consultar por genero. O genero é passado na url
	@GetMapping("/genero/{genero}")
	public ArrayList<Cliente> pesquisarClientePorGenero(
			@PathVariable("genero") Character genero){
		//Realiza a consulta no service
		return clienteService.pesquisarClientePorGeneroService(genero);
	}
}
