package com.testeTecnicoBackend.SeaTecnologia.service;

import com.testeTecnicoBackend.SeaTecnologia.entity.Solicitation;
import com.testeTecnicoBackend.SeaTecnologia.entity.User;
import com.testeTecnicoBackend.SeaTecnologia.enums.SolicitationStatus;
import com.testeTecnicoBackend.SeaTecnologia.repository.SolicitationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SolicitationService {

    private final SolicitationRepository solicitationRepository;

    public SolicitationService(SolicitationRepository solicitationRepository) {
        this.solicitationRepository = solicitationRepository;
    }

    public Solicitation createDraft(User client) {
        Solicitation solicitation = Solicitation.builder()
                .client(client)
                .status(SolicitationStatus.DRAFT)
                .currentStep(1)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return solicitationRepository.save(solicitation);
    }
}