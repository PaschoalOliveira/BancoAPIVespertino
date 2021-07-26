package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cliente;
import com.example.demo.models.InstituicaoFinanceira;
import com.example.demo.repository.InstituicaoRepository;

//Define que esta cçasse é um bean
@Service
public class InstituicaoService {

	//Cria um ponto de injeção
	@Autowired
	InstituicaoRepository instituicaoRepository;

	//Método responsável por fazer o casting de List para ArrayList
	//e consumir o jparepository
	public ArrayList<InstituicaoFinanceira> findAll(){	
		return (ArrayList<InstituicaoFinanceira>) instituicaoRepository.findAll();
	}
	
	//Método no service responsável por chamar o Repository e faer o casting para ArrayList
	public ArrayList<InstituicaoFinanceira> findByName(String nome){
		return (ArrayList<InstituicaoFinanceira>) instituicaoRepository.findByName(nome);
	}
	
	//Método que busca por id. Retorna um Optional e irá estourar erro caso esteja vazio
	public InstituicaoFinanceira findById(Integer identifier){	
		Optional<InstituicaoFinanceira> opInstituicao
		= instituicaoRepository.findById(identifier);
		
		if(opInstituicao.isEmpty()) {
			//Estourar mensagem de erro
		}
		return opInstituicao.get();
	}
	//Método responsável pelo delete
	public void deleteById(Integer identifier){	
		
		instituicaoRepository.deleteById(identifier);
		
	}
	
	
	
	//Chama o save do repositório responsável por
	//inserir ou atualizar
	public void save(InstituicaoFinanceira instituicao) {
		instituicaoRepository.save(instituicao);
	}
	
	
	//Método responsável por realizar somente o update
	//Faz o update apenas quando existe no banco
	public void update(InstituicaoFinanceira instituicao) throws Exception{
		//Resgata o optional de findById
		Optional<InstituicaoFinanceira> opInstituicao
		= instituicaoRepository.findById(instituicao.getIdentifier());
		//VErifica se o optional possui uma isntituicao em seu involucro
		if(opInstituicao.isPresent()) {
			//Aso exista ele salva. O PUT deve salvar somente quando existir uma instituicao
			instituicaoRepository.save(instituicao);			
		}else {
			//Gera uma exceção para indicar que a instituição não existe
			System.out.println("Não existe instituição com o código");
			throw new Exception();
		}
	}
	
	
	
}
