package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
