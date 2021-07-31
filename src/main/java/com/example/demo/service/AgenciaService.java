package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.models.Agencia;
import com.example.demo.repository.AgenciaRepository;
import com.example.demo.specification.AgenciaSpecification;

@Service
public class AgenciaService {

	@Autowired
	AgenciaRepository agenciaRepository;
	
	//Método do service responsável por chamar o findAll com paginação e filtros
	public Page<Agencia> buscarTodos(Integer numeroPagina, Integer itensPorPagina, String campoOrdenacao, String ordemOrdenacao,
									Integer id, String nome){
		
		AgenciaSpecification specAgencia = new AgenciaSpecification(id, nome);
		
		//Criação do objeto PageRequest responsável por conter os parâmetros de ordenação e paginação
		PageRequest pageRequest = PageRequest.of(numeroPagina, itensPorPagina, Direction.fromString(ordemOrdenacao), campoOrdenacao); 
		
		//Chamada do findAll com o objeto pageRequest que contém as ifnormações de paginação e ordenação
		return agenciaRepository.findAll(specAgencia, pageRequest);
	}

	public ArrayList<Agencia> buscarPorFiltro(Integer id, String nome){
		
		ArrayList<Agencia> agencias = new ArrayList<Agencia>();
			
		if(id!= null && nome != null) {
			agencias = (ArrayList<Agencia>) agenciaRepository.findByNomeAndId(nome,id);	
		}if(id == null) {
			agencias = (ArrayList<Agencia>) agenciaRepository.findByNome(nome);		
		}else if(nome == null) {
			agencias.add(agenciaRepository.findById(id).get());
		}
		
		return agencias;
	}
	
	public ArrayList<Agencia> buscarPorFiltroSpecification(Integer id, String nome){
		
		AgenciaSpecification specAgencia = new AgenciaSpecification(id, nome);
	
		return (ArrayList<Agencia>)agenciaRepository.findAll(specAgencia);
	}

}
