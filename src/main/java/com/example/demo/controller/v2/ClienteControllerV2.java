package com.example.demo.controller.v2;

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
@RequestMapping("/v2/clientes")
//Controller responsável pelo recurso Cliente
public class ClienteControllerV2 {

	//Ponto de injeção que permite utilziar o bean nesta classe
	@Autowired
	ClienteService clienteService;

	@GetMapping
	public ArrayList<Cliente> pesquisarTodos() {
		return clienteService.findAll();
	}
	
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
