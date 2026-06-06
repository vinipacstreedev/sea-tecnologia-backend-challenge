package com.testeTecnicoBackend.SeaTecnologia.service;

import com.testeTecnicoBackend.SeaTecnologia.entity.Solicitation;
import com.testeTecnicoBackend.SeaTecnologia.entity.User;
import com.testeTecnicoBackend.SeaTecnologia.enums.SolicitationStatus;
import com.testeTecnicoBackend.SeaTecnologia.repository.SolicitationRepository;
import org.springframework.stereotype.Service;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.Step1RequestDTO;
import java.util.UUID;
import java.time.LocalDateTime;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.Step2RequestDTO;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.ViaCepResponseDTO;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.Step3RequestDTO;
import com.testeTecnicoBackend.SeaTecnologia.enums.Priority;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Service
public class SolicitationService {

    private final SolicitationRepository solicitationRepository;

    private final ViaCepService viaCepService;

    public SolicitationService(
            SolicitationRepository solicitationRepository,
            ViaCepService viaCepService
    ) {
        this.solicitationRepository = solicitationRepository;
        this.viaCepService = viaCepService;
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
    public Solicitation saveStep2(
            UUID solicitationId,
            User client,
            Step2RequestDTO request
    ) {

        Solicitation solicitation = solicitationRepository.findById(solicitationId)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        if (!solicitation.getClient().getId().equals(client.getId())) {
            throw new RuntimeException("Você não pode editar esta solicitação");
        }

        ViaCepResponseDTO address =
                viaCepService.findAddressByCep(request.cep());

        solicitation.setCep(address.cep());
        solicitation.setStreet(address.street());
        solicitation.setNeighborhood(address.neighborhood());
        solicitation.setCity(address.city());
        solicitation.setState(address.state());

        solicitation.setNumber(request.number());
        solicitation.setComplement(request.complement());

        solicitation.setCurrentStep(
                Math.max(solicitation.getCurrentStep(), 2)
        );

        solicitation.setUpdatedAt(LocalDateTime.now());

        return solicitationRepository.save(solicitation);
    }
    public Solicitation saveStep3(
            UUID solicitationId,
            User client,
            Step3RequestDTO request
    ) {

        Solicitation solicitation = solicitationRepository.findById(solicitationId)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        if (!solicitation.getClient().getId().equals(client.getId())) {
            throw new RuntimeException("Você não pode editar esta solicitação");
        }

        solicitation.setPriority(request.priority());

        solicitation.setPreferredDate(
                request.preferredDate()
        );

        solicitation.setEstimatedValue(
                request.estimatedValue()
        );

        solicitation.setTermsAccepted(
                request.termsAccepted()
        );

        solicitation.setCurrentStep(
                Math.max(solicitation.getCurrentStep(), 3)
        );

        solicitation.setUpdatedAt(LocalDateTime.now());

        return solicitationRepository.save(solicitation);
    }
    public Solicitation submit(UUID solicitationId, User client) {
        Solicitation solicitation = solicitationRepository.findById(solicitationId)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        if (!solicitation.getClient().getId().equals(client.getId())) {
            throw new RuntimeException("Você não pode enviar esta solicitação");
        }

        if (solicitation.getStatus() != SolicitationStatus.DRAFT) {
            throw new RuntimeException("Somente solicitações em rascunho podem ser enviadas");
        }

        if (solicitation.getServiceType() == null ||
                solicitation.getTitle() == null ||
                solicitation.getDescription() == null) {
            throw new RuntimeException("Step 1 incompleto");
        }

        if (solicitation.getCep() == null ||
                solicitation.getStreet() == null ||
                solicitation.getNeighborhood() == null ||
                solicitation.getCity() == null ||
                solicitation.getState() == null ||
                solicitation.getNumber() == null) {
            throw new RuntimeException("Step 2 incompleto");
        }

        if (solicitation.getPriority() == null ||
                solicitation.getPreferredDate() == null ||
                solicitation.getEstimatedValue() == null ||
                !Boolean.TRUE.equals(solicitation.getTermsAccepted())) {
            throw new RuntimeException("Step 3 incompleto");
        }

        if (solicitation.getPriority() == Priority.HIGH &&
                solicitation.getEstimatedValue().compareTo(BigDecimal.valueOf(100)) < 0) {
            throw new RuntimeException("Para prioridade alta, o valor estimado deve ser maior ou igual a 100");
        }

        solicitation.setStatus(SolicitationStatus.SUBMITTED);
        solicitation.setSubmittedAt(LocalDateTime.now());
        solicitation.setUpdatedAt(LocalDateTime.now());

        return solicitationRepository.save(solicitation);
    }
}