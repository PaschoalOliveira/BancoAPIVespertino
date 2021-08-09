package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmpregadoDTO;
import com.example.demo.models.Empregado;
import com.example.demo.repository.EmpregadoRepository;
import com.example.demo.specification.EmpregadoSpecification;

@Service
public class EmpregadoService {

	@Autowired
	EmpregadoRepository empregadoRepository;
	
	public Page<EmpregadoDTO> findAll(Integer cpf, String nome, String nomeAgencia, Integer qtdItensPagina, Integer numeroPagina, String direcaoOrdenacao, String campoOrdem){
	
		PageRequest pageRequest = PageRequest.of(numeroPagina, qtdItensPagina,Sort.Direction.valueOf(direcaoOrdenacao),campoOrdem);

		EmpregadoSpecification specEmpregado = new EmpregadoSpecification(cpf,nome,nomeAgencia);
		
		Page<Empregado> empregados = (Page<Empregado>)empregadoRepository.findAll(specEmpregado, pageRequest);
		
		ArrayList<EmpregadoDTO> empregadosDTO = new ArrayList<EmpregadoDTO>();
		for(Empregado empregado : empregados) {
			EmpregadoDTO emprDTO = new EmpregadoDTO();
			emprDTO.createEmpregadoDto(empregado);
			empregadosDTO.add(emprDTO);
		}
		
		
		//Transforma uma lista de empregadoDTO em um Page<EmpregadoDTO>
		PageRequest pageRequest2 = PageRequest.of(numeroPagina, qtdItensPagina,Sort.Direction.valueOf(direcaoOrdenacao),campoOrdem);

		final int start = (int)pageRequest2.getOffset();
		final int end = Math.min((start + pageRequest2.getPageSize()), empregadosDTO.size());
		final Page<EmpregadoDTO> pageEmpregadosDTO = new PageImpl<>(empregadosDTO.subList(start, end), pageRequest2, empregadosDTO.size());
		
		return pageEmpregadosDTO;
	}
	
	public EmpregadoDTO findById(Integer cpf){
		
	    Optional<Empregado> opEmpregado = empregadoRepository.findById(cpf);
	    Empregado empregado = new Empregado();
	    if(opEmpregado.isPresent()) {
	    	empregado = opEmpregado.get();
	    }
		
		EmpregadoDTO empregadoDto = new EmpregadoDTO();
		empregadoDto.createEmpregadoDto(empregado);
		return empregadoDto;
	}
	
	public void save(Empregado e){
		empregadoRepository.save(e);
	}
}
