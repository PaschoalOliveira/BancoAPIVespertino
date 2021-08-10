package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario buscarPorLoginESenha(Usuario usuario) {
		return usuarioRepository.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha()).get();
	}
	
	public Usuario buscarPorLogin(Usuario usuario) {
		return usuarioRepository.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha()).get();
	}
}
