package com.example.demo.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Usuario;
import com.example.demo.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/v1/usuarios")
public class UsuarioControllerV1 {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/loginSenha")
	public ResponseEntity<Usuario>buscarPorLoginSenha(@RequestHeader("login") String login,
													  @RequestHeader("senha") String senha){
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		return new ResponseEntity<Usuario>(
				usuarioService.buscarPorLoginESenha(usuario),
				HttpStatus.OK);
	}

}
