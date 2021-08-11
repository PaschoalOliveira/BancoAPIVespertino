package com.example.demo.config.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.example.demo.BancoApiApplication;
import com.example.demo.models.UsuarioApi;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	//Define um valor default a partir de uma propriedade do applcaition.properties
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	//Define um valor default a partir de uma propriedade do applcaition.properties
	@Value("${security.jwt.assinatura}")
	//Chave utilziuada para encriptografia do token
	private String chaveAssinatura;
	
	//Método responsável por gerar um token
	public String gerarToken(UsuarioApi usuarioApi) {
		long expString = Long.valueOf(expiracao);
		
		//Determina o tmepo de expiração como daqui a 30 minutos a partir do que está configurado no application.properties
		LocalDateTime dataHoraExpiracao = LocalDateTime.now()
				.plusMinutes(expString);
		//Transforma um LocalDateTime em um Date
		Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
	
		//Constroi o token a partir da chaveAssinatura,expiracao e login do usuário
		return Jwts.builder()
				.setSubject(usuarioApi.getLogin())
				.setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
				.compact();
	}
	
	public Claims obterDados(String token) throws ExpiredJwtException {
		return Jwts.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	
	//Método responsável por identificar se o token é válido
	public boolean tokenValido(String token) {
		try {
			Claims claims = obterDados(token);
			Date dataExpiracao = claims.getExpiration();
			//Transformar um Date em um LocalDateTime
			LocalDateTime localDt = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
			//Verifica se o token já foi expirado
			return !LocalDateTime.now().isAfter(localDt);
		} catch (Exception e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) this.obterDados(token).getSubject();
		
	}
	
}
