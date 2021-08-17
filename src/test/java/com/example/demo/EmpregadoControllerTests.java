package com.example.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.EmpregadoDTO;

//Criou a anotação indicando que será um teste
//Usou o argumento indicando a classe principal do springboot
//Usou como argumento a indicação que o Ambiente Web desse teste usará uma porta aleatória
@SpringBootTest(classes = BancoApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class EmpregadoControllerTests {

	//Colocamos um ponto de injeção de uma classe do springFramework que consegue executar operaçções HTTP
	//em determinada url. Como um Postman
	@Autowired
	TestRestTemplate testRestTemplate;
	
	//Método responsável por executar a consulta
	//Utiliza como parâmetro a url, o verbo HTTP e o tipo de Retorno
	public ResponseEntity<EmpregadoDTO> getObjectEmpregadoDTO(String url) {
		//O método exchange é quem faz a requisição. O botão SEND do Postman
		return testRestTemplate.exchange(url, 
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<EmpregadoDTO>() {
				});
	}
	
	//DADO que eu tenha um empregado com o cpf 33 cadastrando na base
	//QUANDO eu pesquisar por este cpf
	//ENTÃO o nome do empregado retornando não deve ser nulo
//	@Test
	public void testaRetornoEmpregado() {
		EmpregadoDTO empregadoDTO =  this.getObjectEmpregadoDTO("/v1/empregados/33").getBody();
		assertNotNull(empregadoDTO.getNome());
	}
}
