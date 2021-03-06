package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Anotação para mapear o meu model com minha tabela
@Entity(name="cliente")
public class Cliente {
	
	//Anotação para definir que o campo cpf é o id da tabela
	@Id
	private Integer cpf;
	//Anotação para definir que o campo nome é apenas uma coluna
	@Column
	private String nome;
	@Column
	private Character sexo;
	@Column
	private Integer telefone;
	
	//Anotação referente a definição de quem detém o mapeamento.
	//Nesse caso é a PROPRIEDADE cliente de Conta
	@OneToMany(mappedBy="cliente", fetch = FetchType.EAGER)
	List<Conta> contas;
	
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
	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	public List<Conta> getContas() {
		return contas;
	}
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}
