package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.UsuarioApi;

public interface UsuarioApiRepository extends JpaRepository<UsuarioApi, Integer>{

	Optional<UsuarioApi> findByLogin(String login);
}
