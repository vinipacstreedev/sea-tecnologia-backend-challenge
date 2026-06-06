package com.testeTecnicoBackend.SeaTecnologia.dto.solicitation;

import jakarta.validation.constraints.NotBlank;

public record AnalysisDecisionDTO(

        @NotBlank(message = "Comentário é obrigatório")
        String comment

) {
}