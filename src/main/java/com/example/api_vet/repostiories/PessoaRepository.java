package com.example.api_vet.repostiories;

import com.example.api_vet.models.PessoaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends MongoRepository<PessoaModel, String> {
    Optional<PessoaModel> findByCpf(String cpf);
}
