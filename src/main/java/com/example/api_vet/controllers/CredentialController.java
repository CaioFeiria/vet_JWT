package com.example.api_vet.controllers;

import com.example.api_vet.dtos.LoginRequest;
import com.example.api_vet.models.ResponseToken;
import com.example.api_vet.models.UserModel;
import com.example.api_vet.security.JwtTokenProvider;
import com.example.api_vet.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/logar")
@Tag(name = "Validar Login", description = "End point post para validar login e pegar o token de autorização.")
public class CredentialController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtTokenProvider jwt;

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {

            Optional<UserModel> usuario = service.authenticate(loginRequest.getUser(), loginRequest.getPassword());
            String token = jwt.generateToken(usuario.get().getUser());

            return ResponseEntity.ok(new ResponseToken("Authenticated", token));

        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }
}