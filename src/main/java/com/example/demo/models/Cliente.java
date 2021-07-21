package com.example.demo.models;

public class Cliente {

	private String nome;
	private Integer cpf;
	private Character sexo;
	
	public Cliente() {
		
	}
	//Construtor que define nome e cpf
	public Cliente(String nome, Integer cpf) {
		this.setNome(nome);
		this.setCpf(cpf);
	}
	
	public Cliente(String nome, Integer cpf, Character sexo) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSexo(sexo);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getCpf() {
		return cpf;
	}
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}
}
