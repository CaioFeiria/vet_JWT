package com.example.api_vet.services;

import com.example.api_vet.models.ServicoModel;
import com.example.api_vet.repostiories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public ServicoModel save(ServicoModel serv) {
        return repository.save(serv);
    }

    public ServicoModel update(String id, ServicoModel serv) {
        ServicoModel exists = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servico não encontrado com id: " + id));

        if (exists.getId().equals(id)) {
            return repository.save(serv);
        } else {
            throw new RuntimeException("O ID da requisição não confere com o ID do corpo");
        }
    }

    public List<ServicoModel> getAll() {
        return repository.findAll();
    }

    public ServicoModel getById(String id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Servico não encontrado com id: " + id)
                );
    }

    public List<ServicoModel> getByAnimalId(String id) {
        return repository.findByAnimalId(id);
    }

    public void deleteById(String id) {
        ServicoModel servico = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Servico não encontrado com id: " + id));

        repository.deleteById(id);
    }

}
