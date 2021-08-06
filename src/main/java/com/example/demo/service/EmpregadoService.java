package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmpregadoDTO;
import com.example.demo.models.Empregado;
import com.example.demo.repository.EmpregadoRepository;

@Service
public class EmpregadoService {

	@Autowired
	EmpregadoRepository empregadoRepository;
	
	public ArrayList<EmpregadoDTO> findAll(){
	
		ArrayList<Empregado> empregados = (ArrayList<Empregado>)empregadoRepository.findAll();
		
		ArrayList<EmpregadoDTO> empregadosDTO = new ArrayList<EmpregadoDTO>();
		for(Empregado empregado : empregados) {
			EmpregadoDTO emprDTO = new EmpregadoDTO();
			emprDTO.createEmpregadoDto(empregado);
			empregadosDTO.add(emprDTO);
		}
		return empregadosDTO;
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
