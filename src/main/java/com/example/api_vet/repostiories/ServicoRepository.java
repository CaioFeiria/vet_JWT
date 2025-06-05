package com.example.api_vet.repostiories;

import com.example.api_vet.models.ServicoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends MongoRepository<ServicoModel, String> {
    List<ServicoModel> findByAnimalId(String animalId);
}
