package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.SenhaInvalidaException;
import com.example.demo.models.UsuarioApi;
import com.example.demo.repository.UsuarioApiRepository;


//Classe responsável por resgatar o login a partir do banco
@Service
public class UsuarioServiceApi implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioApiRepository usuarioRepository;
	
	//Método responsável por salvar um novo usuairo no banco de dados chamado através do controller
	public UsuarioApi salvar(UsuarioApi usuario) {
		return usuarioRepository.save(usuario);
	}
	
	//Responsável por realizar a autenticação do usuário e retornar o JsonWebToken
	public UserDetails autenticar(UsuarioApi usuarioApi) {
		UserDetails userDetails = loadUserByUsername(usuarioApi.getLogin());
		//Verifica se a senha resgatada do banco a partir do login é igual a senha passada como parâmetro
		Boolean senhasConferem = encoder.matches(usuarioApi.getSenha(), userDetails.getPassword());
		if(senhasConferem) {
			return userDetails;
		}
		throw new SenhaInvalidaException();
	}
	
	
	@Override
	//Método responsável por carregar o UserDetails partir do banco
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Retorna o Optional de Repository e lança uma exceção caso não exista
		UsuarioApi usuarioApi = usuarioRepository.findByLogin(username).get();
		
		//this.autenticar(usuarioApi);
		
		String[] roles = usuarioApi.isAdmin() ? 
				new String[] {"GESTOR","USUARIO"} :
					new String[] {"USUARIO"};
		
		//Mesma idiea de return new UserDetails(username, senha, roles)
		return User.builder()
				.username(usuarioApi.getLogin())
				.password(usuarioApi.getSenha())
				.roles(roles)
				.build();
	}

}
