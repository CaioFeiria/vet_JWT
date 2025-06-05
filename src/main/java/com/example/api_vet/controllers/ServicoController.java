package com.example.api_vet.controllers;

import com.example.api_vet.models.ServicoModel;
import com.example.api_vet.security.JwtTokenProvider;
import com.example.api_vet.services.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @Autowired
    private JwtTokenProvider jwt;

    @GetMapping
    public ResponseEntity<?> listarServicos(@RequestHeader(value = "Authorization") String token) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.getAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarServico(@RequestHeader(value = "Authorization") String token,
                                           @PathVariable String id) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.getById(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<?> buscarServicosPorAnimal(@RequestHeader(value = "Authorization") String token,
                                                     @PathVariable String animalId) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.getByAnimalId(animalId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @PostMapping
    public ResponseEntity<?> criarServico(@RequestHeader(value = "Authorization") String token,
                                          @Valid @RequestBody ServicoModel servico) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(servico));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarServico(@RequestHeader(value = "Authorization") String token,
                                              @PathVariable String id,
                                              @Valid @RequestBody ServicoModel servico) {
        if (jwt.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.update(id, servico));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarServico(@RequestHeader(value = "Authorization") String token,
                                            @PathVariable String id) {
        if (jwt.validateToken(token.substring(7))) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated User");
    }
}