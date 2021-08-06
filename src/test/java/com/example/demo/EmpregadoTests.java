package com.example.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.EmpregadoDTO;
import com.example.demo.service.EmpregadoService;

@SpringBootTest
public class EmpregadoTests {
	
	@Autowired
	EmpregadoService empregadoService;
	

	//DADO que eu tenha um empregado com cpf existente
	//QUANDO eu buscar por este cpf
	//ENTÃO deve retornar um empregado com o nome não nulo
	@Test
	public void verificarEmpregadoPorCPF() {
		EmpregadoDTO empregadoDTO = empregadoService.findById(33);
		assertNotNull(empregadoDTO.getNome());
	}

}
