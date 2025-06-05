package com.example.api_vet.controllers;

import com.example.api_vet.models.PessoaModel;
import com.example.api_vet.security.JwtTokenProvider;
import com.example.api_vet.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @Autowired
    private JwtTokenProvider jwt;

    @GetMapping
    public ResponseEntity<?> listarPessoas(@RequestHeader(value = "Authorization") String token) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.getAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPessoa(@RequestHeader(value = "Authorization") String token,
                                          @PathVariable String id) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.getById(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @PostMapping
    public ResponseEntity<?> criarPessoa(@RequestHeader(value = "Authorization") String token,
                                         @Valid @RequestBody PessoaModel pessoa) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pessoa));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@RequestHeader(value = "Authorization") String token,
                                             @PathVariable String id,
                                             @Valid @RequestBody PessoaModel pessoa) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.update(id, pessoa));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@RequestHeader(value = "Authorization") String token,
                                           @PathVariable String id) {
        if (jwt.validateToken(token.substring(7))) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }
}
