package com.example.restpost.keycloak.controller;

import com.example.restpost.keycloak.domain.UsuarioOrigem;
import com.example.restpost.keycloak.service.UsuarioOrigemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioOrigemService usuarioOrigemService;

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioOrigem>> findAll() {
        List<UsuarioOrigem> usuarios = usuarioOrigemService.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping("realm/inserir-usuario")
    public void saveOnRealm() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        usuarioOrigemService.saveOnRealm();
    }

}
