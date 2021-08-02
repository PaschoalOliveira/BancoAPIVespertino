package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Empregado;
import com.example.demo.repository.EmpregadoRepository;

@Service
public class EmpregadoService {

	@Autowired
	EmpregadoRepository empregadoRepository;
	
	public ArrayList<Empregado> findAll(){
	
		return (ArrayList<Empregado>)empregadoRepository.findAll();
	}
	
	public void save(Empregado e){
		empregadoRepository.save(e);
	}
}
