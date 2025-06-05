package com.example.api_vet.models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicoModel {

    @Id
    private String id;

    @NotBlank(message = "O tipo do serviço é obrigatório")
    private String tipo;

    @NotNull(message = "A data é obrigatória")
    private LocalDateTime data;

    @NotNull(message = "O valor é obrigatório")
    @Min(value = 0, message = "O valor deve ser maior ou igual a 0")
    private Double valor;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @NotBlank(message = "O ID do animal é obrigatório")
    private String animalId;

}
