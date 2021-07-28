package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Agencia;
import com.example.demo.models.Conta;
import com.example.demo.models.InstituicaoFinanceira;
import com.example.demo.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	
	public ArrayList<Conta> findAll(){
		ArrayList<Conta> contas = new ArrayList<Conta>();
		
		contas =  (ArrayList<Conta>) contaRepository.findAll();
	
		for(Conta conta : contas) {
			System.out.println(conta.getCliente().getNome());
		}
		return contas;
	}
}
