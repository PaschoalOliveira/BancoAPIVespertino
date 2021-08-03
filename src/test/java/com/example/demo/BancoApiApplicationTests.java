package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.models.Agencia;
import com.example.demo.models.InstituicaoFinanceira;
import com.example.demo.service.ClienteService;
import com.example.demo.service.CryptoService;
import com.example.demo.service.InstituicaoService;

@SpringBootTest
class BancoApiApplicationTests {

	@Autowired
	InstituicaoService instituicaoService;
	
	@Autowired 
	ClienteService clienteService;
	
	@MockBean
	CryptoService cryptoService;
	
	@BeforeEach
	void setup() {
		when(cryptoService.getBitCoinPrice()).thenReturn(3.5);
	}
	
	//DADO que eu tenho um cliente com um cpf que existe
	//QUANDO eu consultar por este cpf
	//ENTÃO não deve retornar um cliente com a propriedade nome nula
	@Test
	void verificarRetornoClientePorNome() {
		ClienteDTO clienteDto = clienteService.findById(123457);
		
		assertNotNull(clienteDto.getNome());
	}
	
	//DADO que eu tenho uma instituição com o nome
	//QUANDO eu inserir essa instituição
	//ENTÃO é o identificador deve ser retornado e não nulo
	@Test
	void inserirInstituicaoFinanceira() {
		
		InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
		instituicao.setName("Zequinha Banco");
		
		InstituicaoFinanceira instituicaoRetorno = instituicaoService.save(instituicao);
		
		/* A linha abaixo
		if(instituicaoRetorno.getIdentifier() != null) {
			System.out.println("meu teste passou");
		}
		*/	
		//Método responsável por verificar se O identificador é diferente de nulo
		assertNotEquals(null, instituicaoRetorno.getIdentifier());
	}
	
	
	//DADO que eu tenho uma instituição sem o nome
	//QUANDO eu inserir essa instituição
	//ENTÃO é retornado uma exceção DataIntegrityViolationException
	@Test
	void inserirInstituicaoFinanceiraSemNome() {
		
		InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
		instituicao.setAgencias(new ArrayList<Agencia>());
		
		//Método que verifica se determinado método gera uma exceção
		assertThrows(DataIntegrityViolationException.class, () -> instituicaoService.save(instituicao));
	
	}
	
	//DADO que eu tenho uma instituição não salva
	//QUANDO realizar uma atualização
	//ENTÃO é retornada uma exceção com a mensagem "Não existe instituição com o código informado"
	@Test
	void  atualizarInstituicaoFinanceiraNaoExistente() {
		
		InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
		instituicao.setIdentifier(20012032);
		
		//Método que verifica se determinado método gera uma exceção e resgata a exceção gerada
		Exception exception = assertThrows(Exception.class, () -> instituicaoService.update(instituicao));
		
		/*
		if("Não existe instituição com o código informado".equals(exception.getMessage())) {
			System.out.println("Teste passou");
		}else {
			System.out.println("Teste não passou");
		}
		*/
		//VErifica se a msg da exceção gerada é igual adetermina msg esperada
		assertEquals("Não existe instituição com o código informado", exception.getMessage());
	
	}
}
