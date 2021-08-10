package com.example.demo.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.config.security.JwtService;
import com.example.demo.dto.CredenciaisDto;
import com.example.demo.dto.TokenDto;
import com.example.demo.exceptions.SenhaInvalidaException;
import com.example.demo.models.UsuarioApi;
import com.example.demo.service.UsuarioServiceApi;

@RestController
@RequestMapping("/api/usuariosApi")
public class UsuarioApiController {

	@Autowired
	private UsuarioServiceApi usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	//Método no controller responsável por salvar um novo usuário de api
	public ResponseEntity<UsuarioApi> salvar(@RequestBody UsuarioApi usuario){
		
		//Codifica a senha passada
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return new ResponseEntity<UsuarioApi>(usuarioService.salvar(usuario), HttpStatus.CREATED);
	}
	
	//Método responsável por gerar um token para um usuário válido
	@PostMapping("/auth")
	public TokenDto autenticar(@RequestBody CredenciaisDto credenciais) {
		try {
			UsuarioApi usuario = new UsuarioApi();
			usuario.setLogin(credenciais.getLogin());
			usuario.setSenha(credenciais.getSenha());
			
			UserDetails userDetails = usuarioService.autenticar(usuario);
			
			String token = jwtService.gerarToken(usuario);
			
			return new TokenDto(usuario.getLogin(), token);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
}
