package com.example.api_vet.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "O usuário é obrigatório.")
    private String user;

    @NotBlank(message = "A senha é obrigatória.")
    private String password;
}
