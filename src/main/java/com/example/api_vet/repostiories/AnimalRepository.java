package com.example.api_vet.repostiories;

import com.example.api_vet.models.AnimalModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends MongoRepository<AnimalModel, String> {
    List<AnimalModel> findByPessoaId(String pessoaId);
}