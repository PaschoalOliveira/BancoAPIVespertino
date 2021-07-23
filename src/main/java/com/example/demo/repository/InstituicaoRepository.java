package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.InstituicaoFinanceira;

//Repository que vai ser transforamnd oem Bean pelo JPA e que conterá
//operações padrões que são estendidas do JPARepository
public interface InstituicaoRepository extends JpaRepository<InstituicaoFinanceira, Integer>{

}
