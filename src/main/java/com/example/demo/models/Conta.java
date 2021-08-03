package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.example.demo.utils.UtilsUrl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="conta")
public class Conta {

	@Id
	@Column(name="id_conta")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Double saldo;
	
	@Column
	private Double saldoBitCoin;
	
	//Anotação para definir que há muitas contas para um cliente
	@ManyToOne
	//Anotação que define a coluna responsável pela junção
	@JoinColumn(name="cpf")
	//Anotação para que a propriedade contas de CLIENTE seja ignorada
	@JsonIgnoreProperties("contas")
	private Cliente cliente;
	
	@Transient
	private String uriCliente;
	public String getUriCliente() {
		return new UtilsUrl().getUri("/v2/clientes", this.getCliente().getCpf()).toString();
	}
	
	@ManyToOne
	@JoinColumn(name="id_instituicao")
	@JsonIgnoreProperties("contas")
	private InstituicaoFinanceira instituicao;

	public Conta() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public InstituicaoFinanceira getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoFinanceira instituicao) {
		this.instituicao = instituicao;
	}

	public Double getSaldoBitCoin() {
		return saldoBitCoin;
	}

	public void setSaldoBitCoin(Double saldoBitCoin) {
		this.saldoBitCoin = saldoBitCoin;
	}

	
}
