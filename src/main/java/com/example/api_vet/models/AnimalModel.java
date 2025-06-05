package com.example.api_vet.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalModel {

    @Id
    private String id;

    @NotBlank(message = "O nome do animal é obrigatório")
    private String nome;

    @NotBlank(message = "A espécie do animal é obrigatória")
    private String especie;

    @NotBlank(message = "A raça do animal é obrigatória")
    private String raca;

    @NotNull(message = "A idade do animal é obrigatória")
    @Min(value = 0, message = "A idade deve ser maior ou igual a 0")
    private Integer idade;

    @NotBlank(message = "O ID do tutor é obrigatório")
    private String pessoaId;
}
