package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.config.security.JwtAuthFilter;
import com.example.demo.config.security.JwtService;
import com.example.demo.service.UsuarioServiceApi;

@EnableWebSecurity
//Classe responsável por definir questões de autenticação e autorização
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	//Classe responsável por buscar as informações de usuário no banco. Classe implementada por nós
	UsuarioServiceApi usuarioServiceApi;
	
	@Autowired
	JwtService jwtService;
	
	@Bean
	//Encoder responsável por codificar a senha
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	//Filtro responsável por definir o processo de autenticação com JsonWebToken
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioServiceApi);
	}
	
	//Autenticação Antiga em memória
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication()
		.passwordEncoder(passwordEncoder())
		.withUser("usuario")
		.password(passwordEncoder().encode("123"))
		.roles("USER");
	}
	*/
	
	//Autenticação ocorre com dados do banco
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Define a classe de serviço responsável por buscar as informações no banco
		auth.userDetailsService(usuarioServiceApi)
		.passwordEncoder(passwordEncoder());
	}
	
	//Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Forma  ser feita sem jwt e com basic auth
		/*
		http.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/v1/**").hasRole("USUARIO")
		.and()
		.httpBasic();
		*/
		
		//Forma de se autenticar usando o JsonWebToken
		
		http.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/v1/**").hasRole("USUARIO")
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
}
