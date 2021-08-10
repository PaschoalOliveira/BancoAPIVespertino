package com.example.demo.exceptions;

public class SenhaInvalidaException extends RuntimeException {

	public SenhaInvalidaException() {
		super("Senha Invalida");
	}
}
