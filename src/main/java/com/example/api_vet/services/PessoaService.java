package com.example.api_vet.services;

import com.example.api_vet.models.PessoaModel;
import com.example.api_vet.repostiories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public PessoaModel save(PessoaModel animal) {
        return repository.save(animal);
    }

    public PessoaModel update(String id, PessoaModel pessoa) {
        PessoaModel exists = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrado com id: " + id));

        if (exists.getId().equals(id)) {
            return repository.save(pessoa);
        } else {
           throw new RuntimeException("O ID da requisição não confere com o ID do corpo");
        }
    }

    public List<PessoaModel> getAll() {
        return repository.findAll();
    }

    public PessoaModel getById(String id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pessoa não encontrado com id: " + id)
                );
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
