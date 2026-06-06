package com.testeTecnicoBackend.SeaTecnologia.controller;

import com.testeTecnicoBackend.SeaTecnologia.entity.Solicitation;
import com.testeTecnicoBackend.SeaTecnologia.entity.User;
import com.testeTecnicoBackend.SeaTecnologia.service.SolicitationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.Step1RequestDTO;
import jakarta.validation.Valid;
import java.util.UUID;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.Step2RequestDTO;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.Step3RequestDTO;
import java.util.List;
import com.testeTecnicoBackend.SeaTecnologia.dto.solicitation.AnalysisDecisionDTO;

@RestController
@RequestMapping("/solicitations")
public class SolicitationController {

    private final SolicitationService solicitationService;

    public SolicitationController(SolicitationService solicitationService) {
        this.solicitationService = solicitationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Solicitation create(Authentication authentication) {
        User client = (User) authentication.getPrincipal();

        return solicitationService.createDraft(client);
    }
    @PutMapping("/{id}/step1")
    public Solicitation saveStep1(
            @PathVariable UUID id,
            @RequestBody @Valid Step1RequestDTO request,
            Authentication authentication
    ) {
        User client = (User) authentication.getPrincipal();

        return solicitationService.saveStep1(id, client, request);
    }
    @PutMapping("/{id}/step2")
    public Solicitation saveStep2(
            @PathVariable UUID id,
            @RequestBody @Valid Step2RequestDTO request,
            Authentication authentication
    ) {

        User client = (User) authentication.getPrincipal();

        return solicitationService.saveStep2(
                id,
                client,
                request
        );
    }
    @PutMapping("/{id}/step3")
    public Solicitation saveStep3(
            @PathVariable UUID id,
            @RequestBody @Valid Step3RequestDTO request,
            Authentication authentication
    ) {

        User client = (User) authentication.getPrincipal();

        return solicitationService.saveStep3(
                id,
                client,
                request
        );
    }
    @PostMapping("/{id}/submit")
    public Solicitation submit(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        User client = (User) authentication.getPrincipal();

        return solicitationService.submit(id, client);
    }
    @GetMapping("/my")
    public List<Solicitation> findMySolicitations(Authentication authentication) {
        User client = (User) authentication.getPrincipal();

        return solicitationService.findMySolicitations(client);
    }
    @GetMapping("/submitted")
    public List<Solicitation> findSubmittedSolicitations() {
        return solicitationService.findSubmittedSolicitations();
    }
    @PostMapping("/{id}/start-analysis")
    public Solicitation startAnalysis(
            @PathVariable UUID id,
            Authentication authentication
    ) {

        User analyst = (User) authentication.getPrincipal();

        return solicitationService.startAnalysis(
                id,
                analyst
        );
    }
    @PostMapping("/{id}/approve")
    public Solicitation approve(
            @PathVariable UUID id,
            @RequestBody @Valid AnalysisDecisionDTO request,
            Authentication authentication
    ) {

        User analyst = (User) authentication.getPrincipal();

        return solicitationService.approve(
                id,
                analyst,
                request
        );
    }
    @PostMapping("/{id}/reject")
    public Solicitation reject(
            @PathVariable UUID id,
            @RequestBody @Valid AnalysisDecisionDTO request,
            Authentication authentication
    ) {

        User analyst = (User) authentication.getPrincipal();

        return solicitationService.reject(
                id,
                analyst,
                request
        );
    }
}
