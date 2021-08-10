package com.example.demo.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.UsuarioServiceApi;

//Filtro - Intercepta requisições e executa alguma operação
public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private UsuarioServiceApi usuarioServiceApi;
	
	public JwtAuthFilter(JwtService jwtService, UsuarioServiceApi usuarioServiceApi) {
		super();
		this.jwtService = jwtService;
		this.usuarioServiceApi = usuarioServiceApi;
	}

	//Este método define o que vai ser feito na interceptação do filtro
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		//Pega do header o valor Authorization que contém o token
		String authorization = request.getHeader("Authorization");
	
		//Nosso token sempre será passado com o Bearer na frente
		if(authorization != null && authorization.startsWith("Bearer")) {
			//Pego a segunda parte do token separado por vírgula
			String token = authorization.split(" ")[1];
			Boolean tokenValido = jwtService.tokenValido(token);
			
			if(tokenValido) {
				String loginUsuario = jwtService.obterLoginUsuario(token);
				UserDetails userDetails =  usuarioServiceApi.loadUserByUsername(loginUsuario);
				//Crio a estrutura de Usuário para ser salvo no contexto do Spring Framework
				UsernamePasswordAuthenticationToken userAuth =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
				userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//Define na estrutura do springBoot o usuário logado
				SecurityContextHolder.getContext().setAuthentication(userAuth);
			}
			
		}
		//Após o filtro realizar o que deseja ele libera a requisição
		filterChain.doFilter(request, response);
	}

}
