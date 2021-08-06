package com.example.demo.dto;

import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.example.demo.models.Cliente;
import com.example.demo.models.Conta;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Integer cpf;
	private String nome;
	private Character sexo;
	private Integer telefone;
	private Double saldo;
	private Double saldoBitCoin;
	
	public void createClienteDto(Cliente cliente) {
		
		this.setCpf(cliente.getCpf());
		this.setNome(cliente.getNome());
		this.setSexo(cliente.getSexo());
		this.setTelefone(cliente.getTelefone());
		
		Double saldo = 0.0;
		Double localSaldoBitcoin = 0.0;
		
		for(Conta conta : cliente.getContas()) {
			saldo = saldo + conta.getSaldo();
			localSaldoBitcoin = localSaldoBitcoin + conta.getSaldoBitCoin();
		}
		this.setSaldo(saldo);
		this.setSaldoBitCoin(localSaldoBitcoin);
	}
	
	public class SaldoConverter extends AbstractConverter<List<Conta>, Double> {

	    @Override
	    protected Double convert(List<Conta> contas) {

	        return contas
	          .stream().mapToDouble(c ->c.getSaldo()).sum();
	    }
	}
	
	public static ClienteDTO createClienteDtoWithModelMapper(Cliente cliente) {
		ModelMapper modelMapper = new ModelMapper();
		
		
		Converter<Cliente, ClienteDTO> converter = new AbstractConverter<Cliente, ClienteDTO>() {
		    @Override
		    protected ClienteDTO convert(Cliente source) {
		        ClienteDTO destination = new ClienteDTO();

		        Double somatorioSaldo = source.getContas().stream().mapToDouble(c ->c.getSaldo()).sum();
		        Double somatorioSaldoBitcoin = source.getContas().stream().mapToDouble(c ->c.getSaldoBitCoin()).sum();

		        destination.setSaldo(somatorioSaldo);
	            destination.setSaldoBitCoin(somatorioSaldoBitcoin);

		        return destination;
		    }
		};
		
		TypeMap<Cliente, ClienteDTO> typeMap = modelMapper.createTypeMap(Cliente.class, ClienteDTO.class);
		
		typeMap.setPostConverter(converter);
		
		//modelMapper.addConverter(converter);
		
		return modelMapper.map(cliente, ClienteDTO.class);
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getSaldoBitCoin() {
		return saldoBitCoin;
	}

	public void setSaldoBitCoin(Double saldoBitCoin) {
		this.saldoBitCoin = saldoBitCoin;
	}
}
