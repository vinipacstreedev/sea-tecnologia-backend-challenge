package com.testeTecnicoBackend.SeaTecnologia.dto.solicitation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Step2RequestDTO(

        @NotBlank(message = "CEP é obrigatório")
        String cep,

        @NotBlank(message = "Número é obrigatório")
        @Size(min = 1, max = 20, message = "Número deve ter entre 1 e 20 caracteres")
        String number,

        String complement

) {
}