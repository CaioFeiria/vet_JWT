package com.example.api_vet.controllers;

import com.example.api_vet.dtos.LoginRequest;
import com.example.api_vet.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cadastrar")
public class UsuarioController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody LoginRequest user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }
}
