package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Cliente;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/clientes")
//Controller responsável pelo recurso Cliente
public class ClienteController {

	//Ponto de injeção que permite utilziar o bean nesta classe
	@Autowired
	ClienteService clienteService;

	//Criar uma rota para um método POST
	@PostMapping
	public void incluirCliente(@RequestBody Cliente cliente) {

		System.out.println(cliente);
	}
	
	//Criando nova rota
	@GetMapping("/pesquisarNome")
	public String pesquisarNome(@RequestParam("mat") Integer matricula) {

		//Realiza a chamada à camada service 
		String nome = clienteService.pesquisarNomeService(matricula);
		return nome;
	}

	//Criando nova rota para pesquisa de Clientes
	@GetMapping("/pesquisarCliente")
	public Cliente pesquisarCliente(@RequestParam("cpf") Integer cpf) {

		//Realiza a chamada à camada service 
		Cliente cliente = clienteService.pesquisarClienteService(cpf);
		return cliente;
	}

	//Cria a rota para consultar por genero. O genero é passado na url
	@GetMapping("/genero/{genero}/cpf/{cpf}")
	public ArrayList<Cliente> pesquisarClientePorGenero(
			@PathVariable("genero") Character genero,
			@PathVariable("cpf") String cpf){

		//Realiza a consulta no service
		return clienteService.pesquisarClientePorGeneroService(genero);
	}

	/*
		@GetMapping("/pesquisaTodos")
		public ArrayList<Cliente> resgataTodos(){
			//Lista de Clientes
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

			//Preenche a lista
			listaClientes.add(new Cliente("Paschoal","1"));
			listaClientes.add(new Cliente("Moises","2"));
			listaClientes.add(new Cliente("Hamilton","3"));
			//Retorna a lista completa
			return listaClientes;
		}
	 */

}
