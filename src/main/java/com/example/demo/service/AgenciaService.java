package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.models.Agencia;
import com.example.demo.repository.AgenciaRepository;

@Service
public class AgenciaService {

	@Autowired
	AgenciaRepository agenciaRepository;
	
	//Método do service responsável por chamar o findAll com paginação
	public Page<Agencia> buscarTodos(Integer numeroPagina, Integer itensPorPagina, String campoOrdenacao, String ordemOrdenacao){
		
		if(ordemOrdenacao.equalsIgnoreCase("DESCENDENTE")) {
			ordemOrdenacao = "DESC";
		}
		
		//Criação do objeto PageRequest responsável por conter os parâmetros de ordenação e paginação
		PageRequest pageRequest = PageRequest.of(numeroPagina, itensPorPagina, Direction.fromString(ordemOrdenacao), campoOrdenacao); 
		
		//Chamada do findAll com o objeto pageRequest que contém as ifnormações de paginação e ordenação
		return agenciaRepository.findAll(pageRequest);
	}

	public ArrayList<Agencia> buscarPorFiltro(Integer id, String nome){
		
		return (ArrayList<Agencia>) agenciaRepository.findByIdGreaterThanEqualOrNomeContaining(id,nome);
	}

}
