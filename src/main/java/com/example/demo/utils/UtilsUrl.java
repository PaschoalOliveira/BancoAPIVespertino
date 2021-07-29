package com.example.demo.utils;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UtilsUrl {

	public URI getUri(String recurso, Integer id) {
		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(recurso + "/{id}").buildAndExpand(id).toUri();
	}
}
