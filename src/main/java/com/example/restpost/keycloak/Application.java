package com.example.restpost.keycloak;

import com.example.restpost.keycloak.domain.UsuarioOrigem;
import com.example.restpost.keycloak.service.UsuarioOrigemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private UsuarioOrigemService usuarioOrigemService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		UsuarioOrigem usuarioOrigem1 = new UsuarioOrigem(null,"Usuario1", "CPF", "Email");
		UsuarioOrigem usuarioOrigem2 = new UsuarioOrigem(null,"Usuario2", "CPF", "Email");
		UsuarioOrigem usuarioOrigem3 = new UsuarioOrigem(null,"Usuario3", "CPF", "Email");

		usuarioOrigemService.save(usuarioOrigem1);
		usuarioOrigemService.save(usuarioOrigem2);
		usuarioOrigemService.save(usuarioOrigem3);
	}
}
