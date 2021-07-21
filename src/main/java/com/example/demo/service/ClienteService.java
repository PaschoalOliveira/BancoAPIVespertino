package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;

//Anotação que transforma esta classe em um Bean que será gerenciado 
//pelo Spring Framework
@Service
//Classe responsável por minhas regras de negócio de cliente
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	//método responsável pelas regras de negócio realitavas a operação
	//e por se conectar com a camada REpository
	public String pesquisarNomeService(Integer matricula) {
		
		String nome = clienteRepository.resgatarNome(matricula);
		return nome;
	}
	
	//Responsavel por executar alguma operação sobre clientes
	//e resgatar os dados no Repository
	public Cliente pesquisarClienteService(Integer cpf) {
		
		Cliente cliente = clienteRepository.resgatarClienteRepository(cpf);
		
		return cliente;
	}
	//Reponsável por executar regras de negócio e consumir o Repository
	public ArrayList<Cliente> pesquisarClientePorGeneroService(Character genero) {
		
		ArrayList<Cliente> clientes = 
				clienteRepository.resgatarClientePorGeneroRepository(Character.toUpperCase(genero));
		
		return clientes;
	}
	
}
