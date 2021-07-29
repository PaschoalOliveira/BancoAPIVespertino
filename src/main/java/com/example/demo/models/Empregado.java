package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "empregado")
public class Empregado {

	@Id
	private Integer cpf;
	
	@Column
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_agencia")
	@JsonIgnoreProperties("empregados")
	private Agencia agencia;
	
}
