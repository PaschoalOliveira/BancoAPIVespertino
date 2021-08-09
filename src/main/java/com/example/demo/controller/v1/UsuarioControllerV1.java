package com.example.demo.controller.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Usuario;

@RestController
@CrossOrigin
@RequestMapping("/v1/usuarios")
public class UsuarioControllerV1 {
	
	//@GetMapping
	//public ResponseEntity<Usuario>buscarPorLoginSenha(@Request)

}
