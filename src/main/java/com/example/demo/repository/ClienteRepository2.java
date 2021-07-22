package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Cliente;

//Repositório que extende o JPARepository e vai ter a repsonsabilidade
//de executar operações em Cliente
public interface ClienteRepository2 extends JpaRepository<Cliente,Integer>{

}
