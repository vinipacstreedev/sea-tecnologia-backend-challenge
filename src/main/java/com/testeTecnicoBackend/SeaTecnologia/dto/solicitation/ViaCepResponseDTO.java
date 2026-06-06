package com.testeTecnicoBackend.SeaTecnologia.dto.solicitation;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ViaCepResponseDTO(

        String cep,

        @JsonProperty("logradouro")
        String street,

        @JsonProperty("bairro")
        String neighborhood,

        @JsonProperty("localidade")
        String city,

        @JsonProperty("uf")
        String state,

        Boolean erro

) {
}