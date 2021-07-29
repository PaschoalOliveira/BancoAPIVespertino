package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agencia {

	private Integer id;
	
	private String nome;
	
	private InstituicaoFinanceira instituicao;
	
}
