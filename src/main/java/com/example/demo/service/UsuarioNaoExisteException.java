package com.example.demo.service;

public class UsuarioNaoExisteException extends Exception {

	public UsuarioNaoExisteException() {
		super("Usuario Não existe");
	}

}
