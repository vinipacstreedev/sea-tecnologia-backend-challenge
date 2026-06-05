package com.testeTecnicoBackend.SeaTecnologia.dto.solicitation;

import com.testeTecnicoBackend.SeaTecnologia.enums.ServiceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Step1RequestDTO(

        @NotNull(message = "Tipo de serviço é obrigatório")
        ServiceType serviceType,

        @NotBlank(message = "Título é obrigatório")
        @Size(min = 3, max = 80, message = "Título deve ter entre 3 e 80 caracteres")
        String title,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(min = 20, max = 1000, message = "Descrição deve ter entre 20 e 1000 caracteres")
        String description

) {
}