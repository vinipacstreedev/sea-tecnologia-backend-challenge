package com.testeTecnicoBackend.SeaTecnologia.service;

import com.testeTecnicoBackend.SeaTecnologia.entity.Solicitation;
import com.testeTecnicoBackend.SeaTecnologia.entity.User;
import com.testeTecnicoBackend.SeaTecnologia.enums.SolicitationStatus;
import com.testeTecnicoBackend.SeaTecnologia.repository.SolicitationRepository;
import org.springframework.stereotype.Service;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.Step1RequestDTO;
import java.util.UUID;
import java.time.LocalDateTime;

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
    public Solicitation saveStep1(UUID solicitationId, User client, Step1RequestDTO request) {
        Solicitation solicitation = solicitationRepository.findById(solicitationId)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        if (!solicitation.getClient().getId().equals(client.getId())) {
            throw new RuntimeException("Você não pode editar esta solicitação");
        }

        if (solicitation.getStatus() != SolicitationStatus.DRAFT) {
            throw new RuntimeException("Só é possível editar solicitação em rascunho");
        }

        solicitation.setServiceType(request.serviceType());
        solicitation.setTitle(request.title().trim());
        solicitation.setDescription(request.description().trim());
        solicitation.setCurrentStep(Math.max(solicitation.getCurrentStep(), 1));
        solicitation.setUpdatedAt(LocalDateTime.now());

        return solicitationRepository.save(solicitation);
    }
}