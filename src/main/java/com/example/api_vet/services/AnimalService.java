package com.example.api_vet.services;

import com.example.api_vet.models.AnimalModel;
import com.example.api_vet.repostiories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public AnimalModel save(AnimalModel animal) {
        return repository.save(animal);
    }

    public AnimalModel update(String id, AnimalModel animal) {
        AnimalModel exists = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com id: " + id));

        if (exists.getId().equals(id)) {
            return repository.save(animal);
        } else {
            throw new RuntimeException("O ID da requisição não confere com o ID do corpo");
        }
    }

    public List<AnimalModel> getAll() {
        return repository.findAll();
    }

    public AnimalModel getById(String id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Animal não encontrado com id: " + id)
                );
    }

    public List<AnimalModel> getAnimaisByPessoaId(String pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

}
