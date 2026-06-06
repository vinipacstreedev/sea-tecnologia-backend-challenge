package com.testeTecnicoBackend.SeaTecnologia.service;

import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.ViaCepResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ViaCepResponseDTO findAddressByCep(String cep) {
        String normalizedCep = cep.replaceAll("\\D", "");

        String url = "https://viacep.com.br/ws/" + normalizedCep + "/json/";

        ViaCepResponseDTO response = restTemplate.getForObject(url, ViaCepResponseDTO.class);

        if (response == null || Boolean.TRUE.equals(response.erro())) {
            throw new RuntimeException("CEP inválido ou não encontrado");
        }

        return response;
    }
}
