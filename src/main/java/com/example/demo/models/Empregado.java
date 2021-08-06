package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="empregado_dependente",
			joinColumns = @JoinColumn(name = "cpf_empregado"),
			inverseJoinColumns = @JoinColumn(name="id_dependente"))
	@JsonIgnoreProperties("empregados")
	private List<Dependente> dependentes;
	
}
