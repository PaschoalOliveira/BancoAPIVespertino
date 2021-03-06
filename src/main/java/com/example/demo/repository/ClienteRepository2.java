package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Cliente;

//Repositório que extende o JPARepository e vai ter a repsonsabilidade
//de executar operações em Cliente
public interface ClienteRepository2 extends JpaRepository<Cliente,Integer>{

	@Query("SELECT c FROM cliente c WHERE sexo LIKE ?1")
	public ArrayList<Cliente> findByGenero(Character genero);
	
	//Consulta que utiliza do || para concatenar % com o valor do parâmetro para 
	//pesquisar por qq parte do telefone
	@Query("SELECT c FROM cliente c WHERE telefone LIKE '%' || ?1 || '%' ")
	public ArrayList<Cliente> pesquisarPorNumero(String numero);
}
