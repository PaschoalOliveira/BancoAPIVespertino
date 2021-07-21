package com.example.demo.service;

public class CalculadoraService {

	public Integer somar(int x, int y) {
		return x + y;
	}
	
	public Double dividir(int x, int qtd) {
		return Double.valueOf(x/qtd);
	}
}
