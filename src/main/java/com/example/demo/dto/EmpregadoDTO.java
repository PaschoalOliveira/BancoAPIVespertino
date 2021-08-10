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
	
	private String nomeInstituicao;
	
	private String nomesDependentes;
	
	public EmpregadoDTO createEmpregadoDto(Empregado empregado) {
		EmpregadoDTO empregadoDTO = new EmpregadoDTO();
		
		empregadoDTO.setCpf(empregado.getCpf());
		empregadoDTO.setNome(empregado.getNome());
		empregadoDTO.setNomeAgencia(empregado.getAgencia() != null ? empregado.getAgencia().getNome() : "");
		
		empregadoDTO.setNomeInstituicao(empregado.getAgencia() != null && empregado.getAgencia().getInstituicao() != null? 
				empregado.getAgencia().getInstituicao().getName() : "");
		
		String nomeDependentesTemp = "";
		
		for(Dependente dependente : empregado.getDependentes()) {
			
			nomeDependentesTemp = nomeDependentesTemp.concat(dependente.getNome());
			nomeDependentesTemp = nomeDependentesTemp.concat(", ");
		}
		
		empregadoDTO.setNomesDependentes(nomeDependentesTemp);
		return empregadoDTO;
	}
}
