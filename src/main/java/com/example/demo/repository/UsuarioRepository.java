package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	public Optional<Usuario> findByLoginAndSenha(String login, String senha);
}
