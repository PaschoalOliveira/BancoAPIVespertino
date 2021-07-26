package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.InstituicaoFinanceira;

//Repository que vai ser transforamnd oem Bean pelo JPA e que conterá
//operações padrões que são estendidas do JPARepository
public interface InstituicaoRepository extends JpaRepository<InstituicaoFinanceira, Integer>{

	//Método que utilzia HQL(Hibernate Query Language) para consultar por nome
	//o parâmetro ?1 é substituido pelo primeiro parâmetro do método findByName
	@Query("SELECT inst FROM instituicao_financeira inst WHERE nome like ?1")
	public List<InstituicaoFinanceira> findByName(String nome);
}
