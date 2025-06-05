package com.example.api_vet.services;

import com.example.api_vet.dtos.LoginRequest;
import com.example.api_vet.models.UserModel;
import com.example.api_vet.repostiories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserModel> findAll() {
        return repository.findAll();
    }

    public UserModel save(LoginRequest user) {
        UserModel userNew = new UserModel();
        userNew.setUser(user.getUser());
        userNew.setPassword(user.getPassword());
        return repository.save(userNew);
    }

    public UserModel getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }

    public Optional<UserModel> getByUser(String user) {
        return repository.findByUser(user);
    }

    public UserModel update(Long id, UserModel updated) {
        UserModel existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        if (existente.getId().equals(id)) {
            repository.save(updated);
        } else {
            throw  new RuntimeException("O ID da requisição não condiz com o ID do corpo da requisição.");
        }

        return updated;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Optional<UserModel> authenticate(String username, String senhaInformada) {
        Optional<UserModel> usuario = repository.findByUser(username);

        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse usuário não possui cadastro.");
        }

        UserModel user = usuario.get();

        if (!user.getUser().equals(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse usuário não possui cadastro.");
        }
        if (!user.getPassword().equals(senhaInformada)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha incorreta");
        }
        return usuario;
    }
}
