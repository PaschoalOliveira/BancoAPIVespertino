package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ClienteRepository2;

//Anotação que transforma esta classe em um Bean que será gerenciado 
//pelo Spring Framework
@Service
//Classe responsável por minhas regras de negócio de cliente
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteRepository2 clienteRepository2;
	
	//Método responsável por fazer o casting e consultar o método
	//de JPaRepository
	public ArrayList<Cliente> findAll(){
		ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();
		listaRetorno = (ArrayList<Cliente>) clienteRepository2.findAll();
		return listaRetorno;
	}
	
	//Método responsável por chamar o meu repository e por tratar o meu Optional
	public Cliente findById(Integer cpf) {
		Optional<Cliente> oCliente;
		oCliente = clienteRepository2.findById(cpf);
		return oCliente.get();
	}
	
	//método responsável pelas regras de negócio realitavas a operação
	//e por se conectar com a camada Repository
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
	
	public void incluirCliente(Cliente cliente) {
		
		clienteRepository.inserirCliente(cliente);
	}
	
}
