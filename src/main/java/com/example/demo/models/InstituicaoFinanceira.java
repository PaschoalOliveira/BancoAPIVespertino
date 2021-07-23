package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Anotação para identificar qual é a tabela
@Entity(name="instituicao_financeira")
public class InstituicaoFinanceira {

	//Anotação para dizer que o campo é um id
	@Id
	//Anotação para indicar o noem da coluna
	@Column(name="id")
	//Anotação par indicar que é um valor gerado por AUTO_INCREMENT do banco
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer identifier;
	
	@Column(name="nome")
	private String name;
	
	//Anotação para indicar que este campo não será mapeado
	@Transient
	//Anotação que indicará que não aparecerá no JSON
	@JsonIgnore
	private String sId;
	
	public InstituicaoFinanceira() {
		
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}
}
