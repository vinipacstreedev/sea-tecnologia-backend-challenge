package com.testeTecnicoBackend.SeaTecnologia.dto.solicitation;

import com.testeTecnicoBackend.SeaTecnologia.enums.Priority;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Step3RequestDTO(

        @NotNull(message = "Prioridade é obrigatória")
        Priority priority,

        @NotNull(message = "Data preferida é obrigatória")
        @FutureOrPresent(message = "Data preferida não pode ser no passado")
        LocalDate preferredDate,

        @NotNull(message = "Valor estimado é obrigatório")
        @DecimalMin(value = "0.0", message = "Valor estimado deve ser maior ou igual a zero")
        BigDecimal estimatedValue,

        @NotNull(message = "Aceite dos termos é obrigatório")
        Boolean termsAccepted

) {
}