package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmpregadoDTO;
import com.example.demo.models.Empregado;
import com.example.demo.repository.EmpregadoRepository;
import com.example.demo.specification.EmpregadoSpecification;

@Service
public class EmpregadoService {

	@Autowired
	EmpregadoRepository empregadoRepository;
	
	@Autowired
	CacheManager cacheManager;
	
	@Cacheable("empregados")
	public Page<EmpregadoDTO> findAll(Integer cpf, String nome, String nomeAgencia, Integer qtdItensPagina, Integer numeroPagina, String direcaoOrdenacao, String campoOrdem){
	
		PageRequest pageRequest = PageRequest.of(numeroPagina, qtdItensPagina,Sort.Direction.valueOf(direcaoOrdenacao),campoOrdem);

		EmpregadoSpecification specEmpregado = new EmpregadoSpecification(cpf,nome,nomeAgencia);
		
		Page<Empregado> pageEmpregados = (Page<Empregado>)empregadoRepository.findAll(specEmpregado, pageRequest);
		
		
		/*
		ArrayList<EmpregadoDTO> empregadosDTOSemLambda = new ArrayList<EmpregadoDTO>();
		for(Empregado empregado : pageEmpregados) {
			EmpregadoDTO emprDTO = new EmpregadoDTO().createEmpregadoDto(empregado);
			empregadosDTOSemLambda.add(emprDTO);
		}
		*/
		
		List<EmpregadoDTO> empregadosDto = pageEmpregados.stream()
				.map(e -> new EmpregadoDTO().createEmpregadoDto(e)).collect(Collectors.toList());
		
		int totalElements = (int) pageEmpregados.getTotalElements();
		
        return new PageImpl<EmpregadoDTO>(empregadosDto, pageRequest, totalElements);
        
		//return pageEmpregadosDTO;
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
	
	public void clearCacheEmpregados() {
		cacheManager.getCache("empregados").clear();
		System.out.println("Limpando cache...");
	}
}
