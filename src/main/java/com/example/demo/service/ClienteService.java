package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ClienteRepository2;
import com.example.demo.repository.ContaRepository;

//Anotação que transforma esta classe em um Bean que será gerenciado 
//pelo Spring Framework
@Service
//Classe responsável por minhas regras de negócio de cliente
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteRepository2 clienteRepository2;
	
	@Autowired
	CryptoService cryptoService;
	
	@Autowired
	ContaRepository contaRepository;
	
	
	//Método responsável por fazer o casting e consultar o método
	//de JPaRepository
	public ArrayList<Cliente> findAll(int page, int size){
		ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();
		
		PageRequest.of(page, size);
		listaRetorno = (ArrayList<Cliente>) clienteRepository2.findAll();

		return listaRetorno;
	}
	
	//Método responsável por chamar o meu repository e por tratar o meu Optional
	public ClienteDTO findById(Integer cpf) {
		Optional<Cliente> oCliente;
		oCliente = clienteRepository2.findById(cpf);
		
		Cliente cliente = oCliente.get();
		
		Double dCotacaoBitcoin = cryptoService.getBitCoinPrice();
		/*
		for(Conta conta : cliente.getContas()) {
			conta.setSaldoBitCoin(conta.getSaldoBitCoin() * dCotacaoBitcoin);
		}
		*/
		cliente.getContas().forEach(c -> 
		c.setSaldoBitCoin(c.getSaldoBitCoin() * dCotacaoBitcoin));
		
		/*Trecho de código
		 * que demonstra como seria se tivéssemos somente cpf em conta
		Conta conta = new Conta();
		Integer cpfRetorno = conta.getCpf();
		Cliente cliente = clienteRepository2.findById(cpfRetorno);
		*/
		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.createClienteDto(cliente);
		//ClienteDTO clienteDto = ClienteDTO.createClienteDtoWithModelMapper(cliente);
		
		return clienteDto;
	}
	
	//método responsável pelas regras de negócio realitavas a operação
	//e por se conectar com a camada Repository
	public String pesquisarNomeService(Integer matricula) {		
		String nome = clienteRepository.resgatarNome(matricula);
		return nome;
	}
	
	public ArrayList<Cliente> pesquisarPorNumero(String numero){
		return clienteRepository2.pesquisarPorNumero(numero);
	}
	
	//Responsavel por executar alguma operação sobre clientes
	//e resgatar os dados no Repository
	public Cliente pesquisarClienteService(Integer cpf) {
		
		Cliente cliente = clienteRepository.resgatarClienteRepository(cpf);
		
		return cliente;
	}
	//Reponsável por executar regras de negócio e consumir o Repository
	public ArrayList<Cliente> pesquisarClientePorGeneroService(Character genero) {
		ArrayList<Cliente> clientes = clienteRepository2.findByGenero(genero);
		return clientes;
	}
	
	public void incluirCliente(Cliente cliente) {
		
		clienteRepository.inserirCliente(cliente);
	}
	
}
