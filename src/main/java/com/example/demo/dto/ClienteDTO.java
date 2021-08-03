package com.example.demo.dto;

import com.example.demo.models.Cliente;
import com.example.demo.models.Conta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
}
