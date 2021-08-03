package com.example.demo.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoService {

	public Double getBitCoinPrice()
	{
		//Url que será consulta. Esta url contém acotação do bitcoin em dólar
		//Em caso de dúvida realize um GET via postman
		String uri = "https://api.coindesk.com/v1/bpi/currentprice.json";

		//Classe responsável por executar um get em uma API e retornar um valor
		RestTemplate restTemplate = new RestTemplate();
		//Consulta um endpoint e retorna uma string com o resultado
		String result = restTemplate.getForObject(uri, String.class);


		Object cotacao = null;
		//Realiza o parse de String para Object
		try {
			//Objeto responsável por fazer o Parser de String para Json e vice-versa
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(result);
			//Resgata a informação de rate dentro dos objetos JSON contidos na resposta
			JSONObject objectBpi = (JSONObject)json.get("bpi");
			JSONObject objectUsd = (JSONObject)objectBpi.get("USD");
			cotacao = objectUsd.get("rate");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//retira a vírgula do resultado obtido e converte para String 
		Double dCotacao = Double.valueOf(cotacao.toString().replace(",", ""));

		return dCotacao;
	}

}
