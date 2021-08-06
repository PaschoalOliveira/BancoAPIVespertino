package com.example.demo.dto;

import com.example.demo.models.Dependente;
import com.example.demo.models.Empregado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpregadoDTO {

    private Integer cpf;
	
	private String nome;
	
	private String nomeAgencia;
	
	private String nomesDependentes;
	
	public void createEmpregadoDto(Empregado empregado) {
		this.setCpf(empregado.getCpf());
		this.setNome(empregado.getNome());
		this.setNomeAgencia(empregado.getAgencia() != null ? empregado.getAgencia().getNome() : "");
		
		String nomeDependentesTemp = "";
		
		for(Dependente dependente : empregado.getDependentes()) {
			
			nomeDependentesTemp = nomeDependentesTemp.concat(dependente.getNome());
			nomeDependentesTemp = nomeDependentesTemp.concat(", ");
		}
		
		this.setNomesDependentes(nomeDependentesTemp);
	}
}
