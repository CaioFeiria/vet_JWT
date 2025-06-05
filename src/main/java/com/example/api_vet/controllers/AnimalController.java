package com.example.api_vet.controllers;

import com.example.api_vet.models.AnimalModel;
import com.example.api_vet.security.JwtTokenProvider;
import com.example.api_vet.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/animais")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @Autowired
    private JwtTokenProvider jwt;

    @GetMapping
    public ResponseEntity<?> listarAnimais(@RequestHeader(value = "Authorization") String token) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.getAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAnimal(@RequestHeader(value = "Authorization") String token,
                                          @PathVariable String id) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.getById(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<?> buscarAnimaisPorPessoa(@RequestHeader(value = "Authorization") String token,
                                                    @PathVariable String pessoaId) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.getAnimaisByPessoaId(pessoaId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @PostMapping
    public ResponseEntity<?> criarAnimal(@RequestHeader(value = "Authorization") String token,
                                         @Valid @RequestBody AnimalModel animal) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(animal));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAnimal(@RequestHeader(value = "Authorization") String token,
                                             @PathVariable String id,
                                             @Valid @RequestBody AnimalModel animal) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.update(id, animal));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAnimal(@RequestHeader(value = "Authorization") String token,
                                           @PathVariable String id) {
        if (jwt.validateToken(token.substring(7))) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }
}